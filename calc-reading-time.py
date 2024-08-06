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

import sys
import json
from pathlib import Path
import csv



ERROR = True # True displays error messages and False should not
DEBUG = True # True displays debug messages and False should not
SIDE_EFFECT = True # True displays Side Effect messages and False should not (recommended True for development)

reading_speed=225 # Configuration for reading speed. Used during data analysis


# Given reading speed and number of words calculate reading time

def calculate_reading_time(num_words, reading_speed):
    """
    Calculate the time it takes to read a given number of words based on a specified reading speed.

    Parameters:
    - num_words: Number of words to be read (int)
    - reading_speed: Reading speed in words per minute (int)

    Returns:
    - reading_time: Time to read the given number of words in minutes (float)
    """
    reading_time = num_words / reading_speed
    return reading_time


def main(param_dict):
    num_words = param_dict["num_words"]
    reading_speed = param_dict["reading_speed"]

    reading_time = calculate_reading_time(num_words, reading_speed)

    print("Reading speed (wpm): " + str(reading_speed))
    print("Number of words found: " + str(num_words))
    print("Calculated reading time (min): {:.2f}".format(reading_time, num_words, reading_speed))

    param_dict["calculated_reading_time_in_minutes"] = reading_time
    return param_dict


class Logger:
    def error(self, message):
        if ERROR == True:
            print(f"ERROR {message}")
    def debug(self, message):
        if DEBUG == True:
            print(f"DEBUG {message}")
    def side_effect(self, message):
        if SIDE_EFFECT == True:
            print(f"SIDE-EFFECT {message}")


if __name__ == "__main__":

    log = Logger() # log object initialization

    word_count = sum(int(row[1]) for i, row in enumerate(csv.reader(open("cache/num-words.csv"))) if i > 0)

    out_dictionary = main({"num_words": word_count, "reading_speed": reading_speed})
    log.debug("Intended output before caching to shareable-file.json: " + str(out_dictionary))

    file_path = Path("cache/shareable-file.json")

    with file_path.open('w') as file: # open the file with the option to write to it
        file.write(json.dumps(out_dictionary)) # write to the file the out_dictionary JSON serialization


    log.side_effect(f"'{file_path}' created successfully.") # Log the file creation side effect
