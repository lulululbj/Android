package luyao.android.plugin

/**
 * Description:
 * Author: luyao
 * Date: 2023/8/14 20:28
 */
object MethodFindUtils {

    private val results = HashMap<String, MutableList<HitMethod>>()
    private val packages = HashSet<String>()
    private var enableLog = false

    fun initConfig(extension: FindExtension) {
        reset()
        enableLog = extension.enableLog
        extension.packages.forEach {
            val packageClassName = it.replace('.', '/')
            packages.add(packageClassName)
        }
    }

    fun filterAndAddMethod(
        buildType: String,
        currentClass: String,
        currentMethod: String,
        invokeClassName: String,
        invokeMethod: String
    ) {
        val isHit = packages.any { invokeClassName.contains(it) }
        if (!isHit) return
        println("Find $currentMethod")
        if (results[buildType] == null) results[buildType] = mutableListOf()
        val d = HitMethod(currentClass, currentMethod, invokeClassName, invokeMethod)
        results[buildType]?.add(d)
    }

    fun end(type: String) {
        if (!results.containsKey(type)) return
        println("<--------findPlugin-$type-start----->")
        println("--> rules [packages]:")
        packages.forEach {
            println("package: [$it]")
        }
        println("------------------------------------->")
        println("--> result: ")
        results[type]?.forEach {
            println(
                "currentClass:[${it.currentClass}],method:[${it.currentMethod}],"
                        + "  invokeClass:[${it.invokeClassName}],method:[${it.invokeMethod}]"
            )
        }
        println("--------findPlugin-$type-end------->")
    }

    private fun reset() {
        results.clear()
        packages.clear()
        enableLog = false
    }
}