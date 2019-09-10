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

    override val realm: Realm = Realm.getDefaultInstance()

}