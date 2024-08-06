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


;; read the file path of the CSV from the command line args
(def csv-file-path (first *command-line-args*))

;; read the CSV line-by-line into a data structure
(def csv-data
    (with-open [reader (io/reader csv-file-path)]
    ;; Babashka aliases clojure.data.csv as csv
    (doall (csv/read-csv reader))))


(def csv {
          :headers (first csv-data)
          :body (rest csv-data)})

(defn csv-print [csv-map]
  (println (csv-map :headers))
  (doseq [row (csv-map :body)] (println row))
  )

;; (csv-print csv)

(def total (reduce (fn [acc v] (+ acc (Integer/parseInt (get v 1)))) 0 (csv :body)))

(println total)