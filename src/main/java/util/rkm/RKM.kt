package util.rkm

import util.graph.State
import util.rkm.addition.*
import kotlin.math.abs

class RKM(private val graph: List<State>) {
    fun calculateRCM(): Pdto {
        val pdto = Pdto(ArrayList(), ArrayList())
        val intensive = getMatrixOfIntensive(graph)
        SIZE_OF_MATRIX = intensive.size
        var t = t0
        val yi = zeroYi
        var ki = Array(P) { DoubleArray(SIZE_OF_MATRIX) }
        var ri: DoubleArray?
        var tao = tao_0
        var sumOfTime = 0.0
        var sumPAndT = 0.0
        var iter = 0
        while (t < T) {
            do {
                ki = getKi(intensive, yi, ki, tao)
                ri = calculateRi(ki)
                if (decreaseStep(ri)) {
                    tao /= 2.0
                    continue
                }
                break
            } while (true)
            if (increaseStep(ri)) {
                tao *= 2.0
            }
            assignNewY1(ki, yi)
            t += tao
            var sumOfGoodState = 0.0
            for (i in yi.indices) {
                if (graph[i].vector[0] >= WORKING_STATES) {
                    sumOfGoodState += yi[i]
                }
            }
            sumOfTime++
            sumPAndT += t * sumOfGoodState
            pdto.t.add(t)
            pdto.y.add(sumOfGoodState)
            println("Номер ітерації: " + iter++ + " t = " + t + "; Значення функції готовності: " + abs(sumOfGoodState))
        }
        println()
        var sum = 0.0
        //double sumGood = 0;
        for (i in yi.indices) {
            sum += yi[i]
            /*if (graph.get(i).getVector()[0] >= WORKING_STATES) {
                sumGood = yi[i];
            }*/print("y" + i + " = " + yi[i] + "; ")
        }

        //System.out.println("\n\nСередній час роботи = " + sumPAndT/sumGood);
        println("Сума ймовірностей для всіх станів системи: $sum")
        pdto.yi = yi
        return pdto
    }

    private fun decreaseStep(ri: DoubleArray?): Boolean {
        for (i in ri!!.indices) {
            if (abs(ri[i]) > eps) {
                return true
            }
        }
        return false
    }

    private fun increaseStep(ri: DoubleArray?): Boolean {
        val oneThirdOfEps = eps / 32
        for (i in ri!!.indices) {
            if (abs(ri[i]) < oneThirdOfEps) {
                return true
            }
        }
        return false
    }

    private fun calculateRi(ki: Array<DoubleArray>?): DoubleArray? {
        if (ki == null) return null
        val ri = DoubleArray(ki[0].size)
        for (i in ki[0].indices) {
            ri[i] = (-2.0 * ki[0][i] + 9.0 * ki[2][i] - 8.0 * ki[3][i] + ki[4][i]) / 30.0
        }
        return ri
    }

    private fun assignNewY1(ki: Array<DoubleArray>, yi: DoubleArray) {
        for (i in ki[0].indices) {
            yi[i] = yi[i] + ONE_SIXTH * ki[0][i] + TWO_THIRD * ki[3][i] + ONE_SIXTH * ki[4][i]
        }
    }

    private fun getKi(
        coefficient: Array<DoubleArray>,
        yi: DoubleArray,
        ki: Array<DoubleArray>,
        tao: Double
    ): Array<DoubleArray> {
        for (i in 0 until P) {
            ki[i] = getKi(coefficient, yi, ki, tao, listOfAddition[i], i)
        }
        return ki
    }

    private fun getKi(
        coefficient: Array<DoubleArray>,
        yi: DoubleArray,
        ki: Array<DoubleArray>,
        tao: Double,
        addition: Addition,
        i: Int
    ): DoubleArray {
        for (j in yi.indices) {
            ki[i][j] = getFValueForI(j, coefficient, ki, yi, addition, tao)
        }
        return ki[i]
    }

    private fun getFValueForI(
        i: Int,
        coefficient: Array<DoubleArray>,
        ki: Array<DoubleArray>,
        yi: DoubleArray,
        addition: Addition,
        tao: Double
    ): Double {
        var sum = (yi[0] + addition.getAddition(0, ki)) * coefficient[i][0]
        for (j in 1 until yi.size) {
            sum += (yi[j] + addition.getAddition(j, ki)) * coefficient[i][j]
        }
        return sum * tao
    }

    fun getWorkingStates(): Int {
        return WORKING_STATES
    }

    companion object {
        private var SIZE_OF_MATRIX = 4
        private const val t0 = 0.0
        private const val T = 130.0
        private const val eps = 0.05
        private const val tao_0 = 0.0005
        private const val P = 5
        var WORKING_STATES = 20
        private const val ONE_SIXTH = 1.0 / 6.0
        private const val TWO_THIRD = 2.0 / 3.0
        private val listOfAddition: MutableList<Addition> = ArrayList()

        init {
            listOfAddition.add(AdditionK1())
            listOfAddition.add(AdditionK2())
            listOfAddition.add(AdditionK3())
            listOfAddition.add(AdditionK4())
            listOfAddition.add(AdditionK5())
        }

        private val zeroYi: DoubleArray
            private get() {
                if (SIZE_OF_MATRIX < 1) {
                    throw RuntimeException("Size of matrix must be greater than 0")
                }
                val yi = DoubleArray(SIZE_OF_MATRIX)
                yi[0] = 1.0
                return yi
            }

        fun getMatrixOfIntensive(graph: List<State>): Array<DoubleArray> {
            val matrixOfIntensive = Array(graph.size) { DoubleArray(graph.size) }
            for (i in graph.indices) {
                val currentState = graph[i]
                val intensity = currentState.intensity
                val keys: Set<Int> = intensity.keys
                for (k in keys) {
                    matrixOfIntensive[k][i] = intensity[k]!!
                }
            }
            return matrixOfIntensive
        }
    }
}
