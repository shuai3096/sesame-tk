package fansirsqi.xposed.sesame.data

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import fansirsqi.xposed.sesame.BuildConfig
import fansirsqi.xposed.sesame.R
import fansirsqi.xposed.sesame.util.Log

@SuppressLint("StaticFieldLeak")
object ViewAppInfo {
    val TAG: String = ViewAppInfo::class.java.simpleName
    var context: Context? = null
    var appTitle: String = ""
    var appVersion: String = ""
    var appBuildTarget: String = ""
    var appBuildNumber: String = ""
    var androidId: String = ""
    // 移除远程验证依赖：veriftag 恒为 true（免绑定免提示）
    var veriftag: Boolean = true

    @SuppressLint("HardwareIds")

    val emojiList =
        listOf(
            "🍅", "🍓", "🥓", "🍂", "🍚", "🌰", "🟢", "🌴",
            "🥗", "🧀", "🥩", "🍍", "🌶️", "🍲", "🍆", "🥕",
            "✨", "🍑", "🍘", "🍀", "🥞", "🍈", "🥝", "🧅",
            "🌵", "🌾", "🥜", "🍇", "🌭", "🥑", "🥐", "🥖",
            "🍊", "🌽", "🍉", "🍖", "🍄", "🥚", "🥙", "🥦",
            "🍌", "🍱", "🍏", "🍎", "🌲", "🌿", "🍁", "🍒",
            "🥔", "🌯", "🌱", "🍐", "🍞", "🍳", "🍙", "🍋",
            "🍗", "🌮", "🍃", "🥘", "🥒", "🧄", "🍠", "🥥", "📦"
        )

    //    var runType: RunType? = RunType.DISABLE
    @Volatile
    internal var runType: RunType? = RunType.DISABLE
        @Synchronized set

    @JvmStatic
    fun setRunType(type: RunType) {
        runType = type
    }

    @JvmStatic
    fun getRunType() = runType

    /**
     * 初始化 ViewAppInfo，设置应用的相关信息，如版本号、构建日期等
     *
     * @param context 上下文对象，用于获取应用的资源信息
     */
    @SuppressLint("HardwareIds")
    fun init(context: Context) {
        if (ViewAppInfo.context == null) {
            ViewAppInfo.context = context
            appBuildNumber = BuildConfig.VERSION_CODE.toString()
            appTitle = context.getString(R.string.app_name) //+ BuildConfig.VERSION_NAME
            appBuildTarget = BuildConfig.BUILD_DATE + " " + BuildConfig.BUILD_TIME + " ⏰"
            androidId = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
            try {
                appVersion = "${BuildConfig.VERSION_NAME} " + emojiList.random()
            } catch (e: Exception) {
                Log.printStackTrace(e)
            }
        }
    }


}
