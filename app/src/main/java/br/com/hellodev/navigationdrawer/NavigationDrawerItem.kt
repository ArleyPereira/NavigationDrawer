package br.com.hellodev.navigationdrawer

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

sealed class NavigationDrawerItem(
    val type: NavigationDrawerType,
    val badge: Int,
    @StringRes val title: Int,
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unselectedIcon: Int
) {

    data object Home : NavigationDrawerItem(
        type = NavigationDrawerType.HOME,
        badge = 0,
        title = R.string.label_home_navigation_drawer_item,
        selectedIcon = R.drawable.ic_home_fill,
        unselectedIcon = R.drawable.ic_home_line,
    )

    data object Search : NavigationDrawerItem(
        type = NavigationDrawerType.SEARCH,
        badge = 0,
        title = R.string.label_search_navigation_drawer_item,
        selectedIcon = R.drawable.ic_search_fill,
        unselectedIcon = R.drawable.ic_search_line,
    )

    data object Notification : NavigationDrawerItem(
        type = NavigationDrawerType.NOTIFICATION,
        badge = 5,
        title = R.string.label_notifications_navigation_drawer_item,
        selectedIcon = R.drawable.ic_notification_fill,
        unselectedIcon = R.drawable.ic_notification_line,
    )

    data object Order : NavigationDrawerItem(
        type = NavigationDrawerType.ORDER,
        badge = 0,
        title = R.string.label_orders_navigation_drawer_item,
        selectedIcon = R.drawable.ic_shopping_fill,
        unselectedIcon = R.drawable.ic_shopping_line,
    )

    data object Favorite : NavigationDrawerItem(
        type = NavigationDrawerType.FAVORITE,
        badge = 0,
        title = R.string.label_favorite_navigation_drawer_item,
        selectedIcon = R.drawable.ic_favorite_fill,
        unselectedIcon = R.drawable.ic_favorite_line,
    )

    enum class NavigationDrawerType {
        HOME,
        SEARCH,
        NOTIFICATION,
        ORDER,
        FAVORITE
    }

    companion object {
        val items = listOf(
            Home,
            Search,
            Notification,
            Order,
            Favorite
        )
    }

}