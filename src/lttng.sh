CLASS_NAME=$1
NUM_OF_THREADS=$2
SIZE_OF_NUMBERS=$3

LTTNG_TRACE_DIR="/home/dipanzan/IdeaProject/Concurrency/traces"

SESSION_NAME="SESSION_CONCCURENCY"

lttng create $SESSION_NAME --output=$LTTNG_TRACE_DIR

lttng enable-event --kernel --all
lttng enable-event --kernel --syscall --all

lttng enable-channel --kernel --num-subbuf=8 --subbuf-size=32M --session=$SESSION_NAME channel0

lttng start

java $CLASS_NAME $NUM_OF_THREADS $SIZE_OF_NUMBERS

lttng destroy

mv $LTTNG_TRACE_DIR/kernel $LTTNG_TRACE_DIR/$SESSION_NAME

chown -R dipanzan:dipanzan $LTTNG_TRACE_DIR
