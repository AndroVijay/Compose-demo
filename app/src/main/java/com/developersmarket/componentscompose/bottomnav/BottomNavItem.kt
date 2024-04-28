package com.developersmarket.componentscompose.bottomnav

import com.developersmarket.componentscompose.R

sealed class BottomNavItem(var title:String, var icon:Int,var screen_route: String){

    object Home1 : BottomNavItem("Home", R.drawable.ic_home,"home")
    object MyChat: BottomNavItem("Chat",R.drawable.ic_chat,"chat")
    object Notification: BottomNavItem("Notification",R.drawable.ic_notification,"notification")
    object Settings: BottomNavItem("Settings",R.drawable.ic_settings,"setting")




}
