package kevin

import android.app.Application
import io.realm.Realm

class ExerciceApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        //see https://realm.io/docs/java/latest/#initializing-realm
        Realm.init(this)
    }
}