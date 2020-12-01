package me.mafrans.adventofcode

class Util {
    companion object {
        fun readResource(path: String): String {
            return String(
                Util::class.java.classLoader
                    .getResourceAsStream(path)!!
                    .readBytes()
            );
        }

        fun readResourceLines(path: String): List<String> {
            return readResource(path).lines()
        }
    }
}

class Timer {
    companion object {
        private var start: Long = 0;

        fun start() {
            start = System.nanoTime();
        }

        private fun nextSeconds(): Double {
            val elapsed = (System.nanoTime() - start) / 1000000000.0
            start = System.nanoTime()
            return elapsed
        }

        private fun nextMillis(): Double {
            val elapsed = (System.nanoTime() - start) / 1000000.0
            start = System.nanoTime()
            return elapsed
        }

        private fun nextMicros(): Double {
            val elapsed = (System.nanoTime() - start) / 1000.0
            start = System.nanoTime()
            return elapsed
        }

        private fun nextNanos(): Double {
            val elapsed = (System.nanoTime() - start).toDouble()
            start = System.nanoTime()
            return elapsed
        }

        fun next(type: TimeType): Double {
            return when(type) {
                TimeType.SECONDS -> nextSeconds()
                TimeType.MILLIS -> nextMillis()
                TimeType.MICROS -> nextMicros()
                TimeType.NANOS -> nextNanos()
            }
        }
    }

    enum class TimeType {
        SECONDS,
        MILLIS,
        MICROS,
        NANOS
    }
}