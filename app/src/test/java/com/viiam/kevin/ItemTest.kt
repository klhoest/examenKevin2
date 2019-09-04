package com.viiam.kevin

import kevin.domain.model.Item
import kevin.presenter.MainPresenter
import kevin.repository.ItemRepositoryStub
import org.junit.Test

import org.junit.Assert.*

class ItemTest {

    /**
     * this is not an automatic test (it never fails)
     * see the console output to assert that it works
     */
    @Test
    fun showLoading() {
        var stubView: MainPresenter.View = object : MainPresenter.View {
            override fun showLoading() {
            }

            override fun hideLoading() {
            }

            override fun refreshAdapter(list: List<Item>) {
                System.out.print(list.toString())
            }
        }
        var presenter: MainPresenter = MainPresenter(stubView, ItemRepositoryStub())
        presenter.loadFullList()
    }
}
