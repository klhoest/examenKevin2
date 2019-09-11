package com.viiam.kevin

import io.reactivex.Observable
import kevin.datasource.ItemService
import kevin.datasource.ItemStorage
import kevin.di.DaggerMainComponent
import kevin.domain.model.Item
import kevin.presenter.MainPresenter
import kevin.repository.ItemRepositoryImpl
import kevin.repository.ItemRepositoryStub
import org.junit.Test

import java.lang.RuntimeException

/**
 * those are not automatic test (it never fails)
 * see the console output to assert that it works
 */
class ItemTest {

    var stubView: MainPresenter.View = object : MainPresenter.View {
        override fun showLoading() {
        }

        override fun hideLoading() {
        }

        override fun refreshAdapter(list: List<Item>) {
            System.out.print(list.toString())
        }
    }

    /**
     * ask the presenter to loadFullList
     * expected : display one item
     */
    @Test
    fun showLoading() {

        var presenter: MainPresenter = MainPresenter(ItemRepositoryStub())
        presenter.initWith(stubView)
        presenter.loadFullList()
    }

    /**
     * ask repository to getList by accessing storage first, then the network
     * expected : display a list of one item then a list of two item
     */
    @Test
    fun itemRepositoryImpl() {
        val storage: ItemStorage = object : ItemStorage {
            override var fullList: List<Item>
                get() {
                    val itemList = mutableListOf(
                            Item(1, 1, "ananas", "fake1")
                    )
                    return itemList
                }
                set(value) {}
        }
        val service: ItemService = object : ItemService {
            override fun getFullItemList(): Observable<List<Item>> {
                val itemList = mutableListOf(
                        Item(1, 1, "ananas", "fake1"),
                        Item(1, 2, "abrico", "fake2")
                )
                return Observable.just(itemList)
            }
        }
        val repo = ItemRepositoryImpl(service, storage)
        repo.getItemFullList().subscribe { list ->
            print(list)
        }
    }

    /**
     * ask repository to getList by accessing storage first, then a faulty network
     * expected : display a list of one item
     */
    @Test
    fun itemRepositoryImplFaultyNetwork() {
        val storage: ItemStorage = object : ItemStorage {
            override var fullList: List<Item>
                get() {
                    val itemList = mutableListOf(
                            Item(1, 1, "ananas", "fake1")
                    )
                    return itemList
                }
                set(value) {}
        }
        val service: ItemService = object : ItemService {
            override fun getFullItemList(): Observable<List<Item>> {
                return Observable.error(RuntimeException("network failed on purpose"))
            }
        }
        val repo = ItemRepositoryImpl(service, storage)
        repo.getItemFullList().subscribe { list ->
            print(list)
        }
    }

    /**
     * ask a main presenter instantiated with dependency injection to loadFullList
     * expected : display a list of 2 item
     */
    @Test
    fun presenterDI() {
        val component = DaggerMainComponent.create()
        val presenter = component.mainPresenter
        presenter.initWith(stubView)
        presenter.loadFullList()
    }
}
