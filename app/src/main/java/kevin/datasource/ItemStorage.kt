package kevin.datasource

import kevin.domain.model.Item

interface ItemStorage {
    var fullList: List<Item>
}