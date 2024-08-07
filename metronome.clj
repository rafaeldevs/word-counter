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


(require '[cheshire.core :as json]
          '[clojure.java.io :as io])

(def f-cache-share "cache/shareable-file.json") ; F_CACHE_SHARE

(defn rewrite-file [file-path new-content]
  (with-open [writer (io/writer file-path)]
    (.write writer new-content))
  (str "IO-Operation: The clojure.java.io/writer function was executed\n" (System/getProperty "user.dir") "/" file-path))

(defn five-min-verses[reading_time]
  (/ reading_time 5))

(def input-map (json/parse-string (slurp f-cache-share) true)) ; true keywords

(defn min->hrs [reading-time-in-min]
  (/ reading-time-in-min 60))




;; TODO theres a better way to do this
(def output-map-0 (assoc input-map :num_five_min_verses (five-min-verses (:calculated_reading_time_in_minutes input-map))))
(def output-map-1 (assoc output-map-0 :calculated_reading_time_in_hours (min->hrs (:calculated_reading_time_in_minutes output-map-0))))



(defn -main [function]
  (cond (= "help" function) (println "Use 'bb tasks' to view the available commands")

        ;; Number of words
        (= "word-total" function) (println (:num_words input-map))

        ;; Reading speed (wpm) 
        (= "reading-speed" function) (println (:reading_speed input-map))

        ;; Calculated reading time (min)
        (= "reading-time-minute" function) (println (:calculated_reading_time_in_minutes input-map))

        ;; Calculated reading time (hr)
        (= "reading-time-hour" function) (println (min->hrs (:calculated_reading_time_in_minutes input-map)))

        ;; Number of five minute verses
        (= "five-minute-verses" function) (println (five-min-verses (:calculated_reading_time_in_minutes input-map)))

        ;; Produces word-count, reading-count, ..., ..., ... , raw to .999-refined 
        (= "file" function) (println (rewrite-file f-cache-share (json/generate-string output-map-1)))
        
        ;; More functions to follow...

        ;; ...

        (= nil function) (println "Error. You must specify a function.")

        :else (println "Invalid command. Use 'bb tasks' to view the available commands")))


(-main (first *command-line-args*))
