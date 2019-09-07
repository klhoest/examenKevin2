package kevin.datasource

import kevin.datasource.storage.RealmBuilder
import kevin.domain.model.Item


class ItemStorageImpl(val realmBuilder: RealmBuilder) : ItemStorage {
    override fun getFullItemList(): List<Item> {
        val result = realmBuilder.realm.where(Item::class.java)
                .findAll()
        return ArrayList(result)
    }
}