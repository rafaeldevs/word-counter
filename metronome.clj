#!/usr/bin/env bb

  ;;  Copyright 2024 Rafael Campo

  ;;  Licensed under the Apache License, Version 2.0 (the "License");
  ;;  you may not use this file except in compliance with the License.
  ;;  You may obtain a copy of the License at

  ;;      http://www.apache.org/licenses/LICENSE-2.0

  ;;  Unless required by applicable law or agreed to in writing, software
  ;;  distributed under the License is distributed on an "AS IS" BASIS,
  ;;  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  ;;  See the License for the specific language governing permissions and
  ;;  limitations under the License.


; Metronome. A tool written in Clojure to process and render out metrics.


(:require '[cheshire.core :as json]
          '[clojure.java.io :as io]
          )

(def f-cache-share "cache/shareable-file.json") ; F_CACHE_SHARE

(defn rewrite-file [file-path new-content]
  (with-open [writer (io/writer file-path)]
    (.write writer new-content)))

(defn five-min-verses[reading_time]
  (/ reading_time 5))

(def input-map (json/parse-string (slurp f-cache-share) true)) ; true keywords

(defn min->hrs [reading-time-in-min]
  (/ reading-time-in-min 60))

;; Describes the map created from the JSON string embedded in the slurped file.
(println "input-map:" input-map)
(println "input-map is a:" (type input-map))
(println "input-map has the following keys:" (keys input-map))


;; TODO get rid of these printing side effects
;; Prints out the some of the key-value pairs defined in the map.
(println "Calculated reading time (min):" (:calculated_reading_time_in_minutes input-map))
(println "Calculated reading time (hr):" (min->hrs (:calculated_reading_time_in_minutes input-map)))
(println "Number of words:" (:num_words input-map))
(println "Reading speed (wpm):" (:reading_speed input-map))

;; Prints a key-value pair after calculating in the runtime defined in this file.
(println "Number of five minute verses:" (five-min-verses (:calculated_reading_time_in_minutes input-map)))

;; TODO theres a better way to do this
(def output-map-0 (assoc input-map :num_five_min_verses (five-min-verses (:calculated_reading_time_in_minutes input-map))))
(def output-map-1 (assoc output-map-0 :calculated_reading_time_in_hours (min->hrs (:calculated_reading_time_in_minutes output-map-0))))

(rewrite-file f-cache-share (json/generate-string output-map-1))
