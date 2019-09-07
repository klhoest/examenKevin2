package kevin.datasource

import kevin.datasource.storage.RealmBuilder
import kevin.domain.model.Item


class ItemStorageImpl(val realmBuilder: RealmBuilder) : ItemStorage {

    override var fullList: List<Item>
        get() {
            val result = realmBuilder.realm.where(Item::class.java)
                    .findAll()
            return ArrayList(result)
        }
        set(inputList) {
            realmBuilder.realm.executeTransaction { realm ->
                inputList.map { item -> realm.copyToRealm(item) }
            }
        }
}