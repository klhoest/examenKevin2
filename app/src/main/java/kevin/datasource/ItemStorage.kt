package kevin.datasource

import kevin.domain.model.Item

interface ItemStorage {
    fun getFullItemList(): List<Item>
}