package kevin.datasource.storage

import io.realm.Realm
import io.realm.RealmConfiguration


class RealmBuilderRAM : RealmBuilder {

    private val ramConfig = RealmConfiguration.Builder()
            .name("ram.realm")
            .inMemory()
            .build()

    override val realm: Realm = Realm.getInstance(ramConfig)
}