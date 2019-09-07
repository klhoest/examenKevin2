package kevin.domain.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * see https://github.com/realm/realm-java/blob/master/examples/kotlinExample/src/main/kotlin/io/realm/examples/kotlin/model/Person.kt
 */
open class Item(
        var albumId: Int = -1,
        @PrimaryKey var id: Int = -1,
        var title: String = "",
        var thumbnailUrl: String = ""
) : RealmObject()