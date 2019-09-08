package kevin.ui

import android.content.Context
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.RecyclerView
import com.viiam.feednemo.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kevin.domain.model.Item
import kotlinx.android.synthetic.main.item_item.view.*

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