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


# Builds a local cache file containing records of files and their corresponding word count
build_cache() {
    local file="$1"
    local num_words="$2"

    
    echo "$file,$num_words" | tee -a cache/num-words.csv
}


process_folder() {
    local path="$1"
    # Check if the path is a directory
    if [ -d "$path" ]; then
        # List all entries in the directory in alphabetical order
        local files=$(find "$path" -type f | sort)
        for file in $files; do
            # Count the number of words in a given file
            local num_words=$(wc -w < "$file")

            # PProcess each file with the count_words functionopulate the cache with the relative file path of the file and the number of words it contains
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

# initialize the cache
echo "file,num_words" > cache/num-words.csv

# Call the main function with the provided folder
process_folder "$1"

echo "---------------------------------------------"

# Calculates the reading time using Python
python3 ./calc-reading-time.py 

# Calculates using Clojure
./metronome.clj

# Pretty prints JSON using jq
jq . cache/shareable-file.json