package br.com.hellodev.navigationdrawer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
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
import br.com.hellodev.navigationdrawer.ui.theme.SecondaryColorDark
import br.com.hellodev.navigationdrawer.ui.theme.TextColorDark
import br.com.hellodev.navigationdrawer.ui.theme.TextColorLight

@Composable
fun NavigationDrawerUI(
    modifier: Modifier = Modifier,
    drawerState: DrawerState,
    items: List<NavigationDrawerItem>,
    drawerIndex: Int,
    isDarkTheme: Boolean,
    onClink: (Int) -> Unit,
    onThemeChange: () -> Unit,
    content: @Composable () -> Unit
) {
    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = NavigationDrawerTheme.colorScheme.primary
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 32.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.user),
                        contentDescription = null,
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(60.dp)
                            .border(1.dp, Color.White, CircleShape),
                        contentScale = ContentScale.Crop,
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .padding(horizontal = 16.dp)
                    ) {
                        Text(
                            text = "Arley Santana",
                            style = TextStyle(
                                color = NavigationDrawerTheme.colorScheme.text,
                                fontSize = 16.sp,
                                fontWeight = FontWeight(500)
                            )
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = "ver perfil",
                            style = TextStyle(
                                color = NavigationDrawerTheme.colorScheme.text,
                                fontWeight = FontWeight(500)
                            )
                        )
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(NavigationDrawerTheme.colorScheme.background)
                ) {
                    HorizontalDivider(
                        color = if (isDarkTheme) {
                            NavigationDrawerTheme.colorScheme.onPrimary
                        } else {
                            Color.Transparent
                        }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    items.forEachIndexed { index, drawerItem ->
                        NavigationDrawerItem(
                            label = {
                                Text(
                                    text = stringResource(id = drawerItem.title),
                                    style = TextStyle(
                                        color = if (isDarkTheme) {
                                            TextColorDark
                                        } else {
                                            TextColorLight
                                        }
                                    )
                                )
                            },
                            selected = index == drawerIndex,
                            onClick = {
                                onClink(index)
                            },
                            modifier = Modifier
                                .padding(NavigationDrawerItemDefaults.ItemPadding),
                            icon = {
                                Icon(
                                    painter = if (index == drawerIndex) {
                                        painterResource(id = drawerItem.selectedIcon)
                                    } else {
                                        painterResource(id = drawerItem.unselectedIcon)
                                    },
                                    contentDescription = null,
                                    tint = if (isDarkTheme) {
                                        TextColorDark
                                    } else {
                                        TextColorLight
                                    }
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
                                selectedContainerColor = NavigationDrawerTheme.colorScheme.onPrimary,
                                unselectedContainerColor = Color.Transparent
                            ),
                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 32.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Dark mode",
                            modifier = Modifier
                                .weight(1f),
                            style = TextStyle(
                                color = NavigationDrawerTheme.colorScheme.text
                            )
                        )

                        Switch(
                            checked = isDarkTheme,
                            onCheckedChange = { onThemeChange() },
                            colors = SwitchDefaults.colors(),
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

@Preview
@Composable
private fun NavigationDrawerUIPreview() {
    val isDarkTheme by remember { mutableStateOf(false) }

    NavigationDrawerTheme(isDarkTheme = isDarkTheme) {
        val drawerState = rememberDrawerState(DrawerValue.Open)
        NavigationDrawerUI(
            drawerState = drawerState,
            items = NavigationDrawerItem.items,
            drawerIndex = 0,
            isDarkTheme = isDarkTheme,
            onClink = {},
            onThemeChange = {},
            content = {}
        )
    }
}