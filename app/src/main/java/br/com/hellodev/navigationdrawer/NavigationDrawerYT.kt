package br.com.hellodev.navigationdrawer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.hellodev.navigationdrawer.ui.theme.NavigationDrawerTheme
import br.com.hellodev.navigationdrawer.ui.theme.PrimaryColorLight

@Composable
fun NavigationDrawerYT(
    modifier: Modifier = Modifier,
    drawerState: DrawerState,
    items: List<NavigationDrawerItem>,
    drawerIndex: Int,
    content: @Composable () -> Unit,
    onClink: (Int) -> Unit
) {
    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = PrimaryColorLight
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 32.dp, horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.user),
                        contentDescription = null,
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(60.dp)
                            .border(1.dp, Color.White, CircleShape),
                        contentScale = ContentScale.Crop
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        Text(
                            text = "Arley Santana",
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 16.sp,
                                fontWeight = FontWeight(500)
                            )
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = "Ver perfil",
                            style = TextStyle(
                                color = Color.Black,
                                fontWeight = FontWeight(500)
                            )
                        )
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                ) {
                    Spacer(modifier = Modifier.height(16.dp))

                    items.forEachIndexed { index, drawerItem ->
                        NavigationDrawerItem(
                            label = {
                                Text(text = stringResource(id = drawerItem.title))
                            },
                            selected = index == drawerIndex,
                            onClick = { onClink(index) },
                            modifier = Modifier
                                .padding(NavigationDrawerItemDefaults.ItemPadding),
                            icon = {
                                Icon(
                                    painter = if (index == drawerIndex) {
                                        painterResource(id = drawerItem.selectedIcon)
                                    } else {
                                        painterResource(id = drawerItem.unselectedIcon)
                                    },
                                    contentDescription = null
                                )
                            },
                            badge = {
                                if (drawerItem.badge > 0) {
                                    Box(
                                        modifier = Modifier
                                            .size(20.dp)
                                            .background(Color.Red, RoundedCornerShape(4.dp)),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = drawerItem.badge.toString(),
                                            style = TextStyle(
                                                fontSize = 12.sp,
                                                color = Color.White
                                            )
                                        )
                                    }
                                }
                            },
                            shape = RoundedCornerShape(8.dp),
                            colors = NavigationDrawerItemDefaults.colors(
                                selectedContainerColor = PrimaryColorLight,
                                unselectedContainerColor = Color.Transparent
                            )
                        )
                    }
                }
            }
        },
        modifier = modifier,
        drawerState = drawerState,
        content = content
    )
}

@Preview(showBackground = true)
@Composable
private fun NavigationDrawerYTPreview() {
    NavigationDrawerTheme {
        val drawerState = rememberDrawerState(DrawerValue.Open)
        var drawerIndex by remember { mutableIntStateOf(0) }

        NavigationDrawerYT(
            drawerState = drawerState,
            items = NavigationDrawerItem.items,
            drawerIndex = drawerIndex,
            content = {},
            onClink = {
                drawerIndex = it
            }
        )
    }
}