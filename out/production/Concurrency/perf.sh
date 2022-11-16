#!/bin/bash

NUM_OF_THREADS=$1
SIZE_OF_NUMBERS=$2

perf stat -aB -e power/energy-pkg/,all_data_cache_accesses:k \
    -e context-switches:k \
    -e cache-misses:k \
    -e cache-references:k \
    -e cpu-cycles:k \
    -e instructions:k \
    -e major-faults:k \
    -e minor-faults:k \
    -e page-faults:k \
    -e all_tlbs_flushed:k \
    java SimpleThread $NUM_OF_THREADS $SIZE_OF_NUMBERS