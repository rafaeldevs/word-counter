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


def validate(parameter_list):
    if len(sys.argv) != 3: # anything other than 2 parameter long is insufficient
        print("ERROR: Reading speed and Number-of-words parameters were not passed.")

    num_words = float(sys.argv[1]) # The the number of words in your corpus of written work. All the blogs. All ideas.
    reading_speed = float(sys.argv[2]) # Somewhere, the average reading speed is 200-250 words per minute.

    return {"num_words": num_words, "reading_speed": reading_speed}


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


log = Logger() # log object initialization


# log.error("This is an error message.") # usage example


ERROR = True # True displays error messages and False should not
DEBUG = True # True displays debug messages and False should not
SIDE_EFFECT = True # True displays Side Effect messages and False should not (recommended True for development)

## File ops
# Opening a file
# file1 = open('myfile.txt', 'w')
# L = ["This is Delhi \n", "This is Paris \n", "This is London \n"]
# s = "Hello\n"
 
# # Writing a string to file
# file1.write(s)
 
# # Writing multiple strings
# # at a time
# file1.writelines(L)
 
# # Closing file
# file1.close()


if __name__ == "__main__":
    
    log.debug("Total number of parameters passed into runtime (i.e." + str(sys.argv[0]) + "): " + str(len(sys.argv)))
    log.debug("Parameter's passed into this runtime (i.e." + str(sys.argv[0]) + ") runtime: " + str(sys.argv))

    parameters = validate(sys.argv)

    log.debug("Parameters after validation: " + str(parameters))

    if parameters != None:
        out_dictionary = main(parameters)
        log.debug("Intended output before caching to shareable-file.json: " + str(out_dictionary))
        
        file_path = Path("cache/shareable-file.json")
        
        with file_path.open('w') as file: # open the file with the option to write to it
            file.write(json.dumps(out_dictionary)) # write to the file the out_dictionary JSON serialization
            
        log.side_effect(f"'{file_path}' created successfully.") # Log the file creation side effect
    else:
        log.error("ERROR: You need to enter the number-of-words and reading-speed parameters.")



