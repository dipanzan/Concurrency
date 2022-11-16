#!/bin/bash

RUNS=$1
CLASS_NAME=$2
NUM_OF_THREADS=$3
SIZE_OF_NUMBERS=$4

perf stat -o results.txt --append -aB -r $RUNS -e power/energy-pkg/,all_data_cache_accesses:k \
    -e context-switches:k \
    -e cache-misses:k \
    -e cache-references:k \
    -e cpu-cycles:k \
    -e instructions:k \
    -e major-faults:k \
    -e minor-faults:k \
    -e page-faults:k \
    -e all_tlbs_flushed:k \
    java $CLASS_NAME $NUM_OF_THREADS $SIZE_OF_NUMBERS