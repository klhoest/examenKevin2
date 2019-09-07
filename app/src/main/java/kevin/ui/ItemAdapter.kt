package kevin.ui

import android.content.Context
import androidx.recyclerview.widget.DiffUtil
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kevin.domain.model.Item

class ItemAdapter(private var item: MutableList<Item>, private val context: Context) :
        RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.item_item, parent, false)
            )


    override fun getItemCount(): Int = item.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.apply {

            beerNameTextView.text = item[position].title
            beerTaglineTextView.text = "id: " + item[position].id

            Glide.with(context)
                    .load(item[position].thumbnailUrl)
                    .placeholder(R.drawable.ic_close_black)
                    .override(200, 300)
                    .into(beerImageTextView)
        }
    }

    fun updateAdapter(updatedList: List<Item>) {
        /*val result = DiffUtil.calculateDiff(BeersDiffCallback(this.item, updatedList))

        this.item = updatedList.toMutableList()
        result.dispatchUpdatesTo(this)*/
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val beerNameTextView: AppCompatTextView = view.item_list_beer_name
    val beerTaglineTextView: AppCompatTextView = view.item_list_beer_tagline
    val beerImageTextView: AppCompatImageView = view.item_list_beer_image
}