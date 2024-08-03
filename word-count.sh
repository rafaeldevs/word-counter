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

    
    echo "$file,$num_words" >> cache/num-words.csv
}

# Function to count words in a file
count_words() {
    local file="$1"
    local num_words=$(wc -w < "$file")
    echo "$num_words - $file"

    build_cache $file $num_words
}

# Main function to process files in a folder recursively
# process_folder() {
#     local folder="$1"
#     find "$folder" -type f -print0 | sort | while IFS= read -r -d '' file; do
#         count_words "$file"
#     done
# }

process_folder() {
    local path="$1"
    # Check if the path is a directory
    if [ -d "$path" ]; then
        # List all entries in the directory in alphabetical order
        local files=$(find "$path" -type f | sort)
        for file in $files; do
            # Process each file
            # echo "Processing $file"
            count_words "$file"
            # Replace the following line with whatever processing you want to do
            # For example, you might want to run `grep`, `sed`, `awk`, or any custom command
        done
    else
        echo "$path is not a directory."
    fi
}

# Check if folder argument is provided
if [ -z "$1" ]; then
    echo "Usage: $0 <folder>"
    exit 1
fi

# initialize the cache
echo "file,num_words" > cache/num-words.csv

# Call the main function with the provided folder
process_folder "$1"

# Sums up the words for every article found
NUM_WORDS=$(python3 -c 'import csv; total = sum(int(row[1]) for i, row in enumerate(csv.reader(open("cache/num-words.csv"))) if i > 0); print(total)')
READING_SPEED=225 # Somewhere, the average reading speed is 200-250 words per minute.

echo "---------------------------------------------"

# Calculates the reading time using Python
python3 ./calc-reading-time.py $NUM_WORDS $READING_SPEED

# Calculates using Clojure
./metronome.clj

printf "-"; sleep 0.2; printf "-"; sleep 0.2; printf "-"; printf "\n"; sleep 0.2
printf "-"; sleep 0.2; printf "-"; sleep 1.0 ; printf "-"; printf "\n"; sleep 0.2

# Pretty prints JSON using jq
jq . cache/shareable-file.json