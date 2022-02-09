package org.jetbrains.research.lupa.pythonAnalysis.callExpressions.analysis.psi

import com.jetbrains.python.psi.PyDecorator
import org.jetbrains.research.lupa.pythonAnalysis.callExpressions.analysis.PyCallExpressionAnalyzer
import org.jetbrains.research.pluginUtilities.util.Extension
import org.jetbrains.research.pluginUtilities.util.ParametrizedBaseWithPythonSdkTest
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import java.io.File

@RunWith(Parameterized::class)
class PyCallExpressionPsiAnalyzerTest : ParametrizedBaseWithPythonSdkTest(
    getResourcesRootPath(
        ::PyCallExpressionPsiAnalyzerTest,
        resourcesRootName = "callExpressionPsiAnalyzerTestData"
    )
) {
    @JvmField
    @Parameterized.Parameter(0)
    var inFile: File? = null

    @JvmField
    @Parameterized.Parameter(1)
    var outFile: File? = null

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}: ({0}, {1})")
        fun getTestData() =
            getInAndOutArray(
                ::PyCallExpressionPsiAnalyzerTest,
                resourcesRootName = "callExpressionPsiAnalyzerTestData",
                inExtension = Extension.PY,
                outExtension = Extension.TXT,
            )
    }

    @Test
    fun testPyCallExpressionFqNamesInFile() {
        testCallExpressionFqNamesInFile(myFixture, inFile!!, outFile!!, PyCallExpressionAnalyzer) { it !is PyDecorator }
    }
}
