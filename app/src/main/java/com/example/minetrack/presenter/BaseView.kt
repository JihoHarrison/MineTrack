package com.example.minetrack.presenter

interface BaseView<PresenterT : BasePresenter> {
    val presenter: PresenterT
}