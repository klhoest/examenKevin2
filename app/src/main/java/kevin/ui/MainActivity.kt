package kevin.ui

import android.os.Bundle
import android.view.View
import com.viiam.feednemo.R
import kevin.di.DaggerMainComponent
import kevin.domain.model.Item
import kevin.presenter.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.appcompat.app.AppCompatActivity

/*
import com.example.manuel.baseproject.ui.adapterlist.BeersAdapter
import com.example.manuel.baseproject.vm.MealsByBeersViewModel
import com.example.manuel.baseproject.vm.model.BeerUI*/

class MainActivity : AppCompatActivity(), MainPresenter.View {

    private lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val component = DaggerMainComponent.create()

        presenter = component.mainPresenter

        //observerLiveDatas()
    }

    override fun onResume() {
        super.onResume()
        presenter.loadFullList()
    }

    override fun showLoading() {
        showSpinner(true)
    }

    override fun hideLoading() {
        showSpinner(false)
    }

    override fun refreshAdapter(list: List<Item>) {
        list?.let {
            recycler_view_item.layoutManager = LinearLayoutManager(this)

            val beersAdapter = ItemAdapter(it.toMutableList(), this)
            recycler_view_item.adapter = beersAdapter
            beersAdapter.updateAdapter(it.toMutableList())

            recycler_view_item.setHasFixedSize(true)
        }
    }

    /*private fun observerLiveDatas() {
        viewModel.beers.observe(this, Observer(::onBeersReceived))
        viewModel.isError.observe(this, Observer { onErrorReceived() })
        viewModel.areEmptyBeers.observe(this, Observer { onEmptyBeersReceived() })
        viewModel.isLoading.observe(this, Observer(::onLoadingStateReceived))
    }

    private fun onBeersReceived(beers: List<BeerUI>) {
        showBeers(beers)
    }*/

    /*private fun showBeers(beersUI: List<BeerUI>?) {
        beersUI?.let {
            recycler_view_beers.layoutManager = LinearLayoutManager(this)

            val beersAdapter = BeersAdapter(it.toMutableList(), this)
            recycler_view_beers.adapter = beersAdapter
            beersAdapter.updateAdapter(it.toMutableList())

            recycler_view_beers.setHasFixedSize(true)
        }
    }*/

    private fun onErrorReceived() {
        // do something
    }

    private fun onEmptyBeersReceived() {
        // do something
    }

    private fun onLoadingStateReceived(isLoading: Boolean) {
        showSpinner(isLoading)
    }

    private fun showSpinner(isLoading: Boolean) {
        main_activity_spinner.apply {
            visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }

}
