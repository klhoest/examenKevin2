package kevin.datasource.storage

import android.content.Context
import io.realm.Realm
import javax.inject.Inject

/**
 * has android dependency
 */
class RealmBuilderImpl @Inject constructor() : RealmBuilder {

    init {
        //Realm.init(context)
    }

    //initialising with Realm.getDefaultInstance() will throw an illegal state exeption on DI
    override val realm: Realm
        get() = Realm.getDefaultInstance()

}