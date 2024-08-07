#!/bin/bash

#    Copyright 2024 Rafael Campo

#    Licensed under the Apache License, Version 2.0 (the "License");
#    you may not use this file except in compliance with the License.
#    You may obtain a copy of the License at

#        http://www.apache.org/licenses/LICENSE-2.0

#    Unless required by applicable law or agreed to in writing, software
#    distributed under the License is distributed on an "AS IS" BASIS,
#    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
#    See the License for the specific language governing permissions and
#    limitations under the License.

F_CACHE_NUM_WORDS=cache/num-words.csv
F_CACHE_SHARE=cache/shareable-file.json


# Builds a local cache file containing records of files and their corresponding word count
build_cache() {
    local file="$1"
    local num_words="$2"

    
    echo "$file,$num_words" | tee -a $F_CACHE_NUM_WORDS
}


process_folder() {
    local path="$1"

    # Initializes the cache CSV file
    echo "file,num_words" > $F_CACHE_NUM_WORDS

    # Check if the path is a directory
    if [ -d "$path" ]; then
        # List all entries in the directory in alphabetical order
        local files=$(find "$path" -type f | sort)
        for file in $files; do
            # Count the number of words in a given file
            local num_words=$(wc -w < "$file")

            # Populate the cache with the relative file path of the the found file and the number of words it contains
            build_cache $file $num_words
        done
    else
        echo "$path is not a directory."
    fi
}

# Check if folder argument is provided otherwise exit
if [ -z "$1" ]; then
    echo "Usage: $0 <folder>"
    exit 1
fi


# Call the main function with the provided folder
process_folder "$1"

echo "---------------------------------------------"

# Calculates the reading time using Python
python3 ./calc-reading-time.py 

# Calculates using Clojure
./metronome.clj file

# Pretty prints JSON using jq
jq . $F_CACHE_SHARE