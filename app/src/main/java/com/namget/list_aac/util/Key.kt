package com.namget.list_aac.util

class Key {
    companion object{
        val cliendKey = "013bf445fde1e4a13fa88d0048ec470a68855bbecd9d6cfd48c2007e09eb1c60"
        val PER_PAGE = 30
    }
    enum class ORDER_BY(val type : String){
        LASTEST("lastest"), POPULAR("popular") , OLDEST("oldest")
    }

}