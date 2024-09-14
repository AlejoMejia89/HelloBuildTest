package com.alejandromejia.hellobuildtest.ui.favorites.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alejandromejia.hellobuildtest.domain.models.user.DUser
import com.alejandromejia.hellobuildtest.domain.utils.NoRippleInteractionSource
import com.alejandromejia.hellobuildtest.domain.utils.noRippleClickable
import com.alejandromejia.hellobuildtest.ui.main.models.ViewModels
import com.alejandromejia.hellobuildtest.utils.getCardColors
import com.alejandromejia.hellobuildtest.utils.getLettersByName
import com.alejandromejia.hellobuildtest.utils.getResourceColor

@Composable
fun FavoriteItems(viewModels: ViewModels, user: DUser) {

    Card(
        modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
        border = BorderStroke(1.dp, color = Color.Black),
        colors = getCardColors()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 5.dp)
                .noRippleClickable {

                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .border(1.dp, color = Color.Black, shape = CircleShape)
                    .background(colorResource(id = getResourceColor(user.id)))
            ) {
                Text(
                    text = user.name.getLettersByName(),
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .wrapContentHeight()
                    .padding(start = 10.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = user.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy((-7).dp),
                ) {
                    Row {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = null,
                            modifier = Modifier
                                .size(20.dp)
                                .padding(end = 5.dp)
                                .align(Alignment.CenterVertically),
                            tint = Color.Gray
                        )
                        Text(
                            text = user.email,
                            fontSize = 12.sp,
                            modifier = Modifier.align(Alignment.CenterVertically),
                            color = Color.Gray
                        )
                    }
                    Row {
                        Icon(
                            imageVector = Icons.Default.Phone,
                            contentDescription = null,
                            modifier = Modifier
                                .size(20.dp)
                                .padding(end = 5.dp)
                                .align(Alignment.CenterVertically),
                            tint = Color.Gray
                        )
                        Text(
                            text = user.phone,
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                    }
                    Row {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = null,
                            modifier = Modifier
                                .size(20.dp)
                                .padding(end = 5.dp)
                                .align(Alignment.CenterVertically),
                            tint = Color.Gray
                        )
                        Text(
                            text = "${user.address.street}, ${user.address.city}",
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                    }
                }

            }
            IconButton(
                onClick = {
                    viewModels.favoritesViewModel.removeFavorite(user)
                },
                modifier = Modifier.align(Alignment.CenterVertically),
                interactionSource = NoRippleInteractionSource()
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = null,
                    modifier = Modifier.size(30.dp),
                )
            }
        }
    }
}