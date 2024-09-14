package com.alejandromejia.hellobuildtest.ui.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alejandromejia.hellobuildtest.R
import com.alejandromejia.hellobuildtest.domain.utils.NoRippleInteractionSource
import com.alejandromejia.hellobuildtest.domain.utils.noRippleClickable
import com.alejandromejia.hellobuildtest.ui.main.models.ViewModels
import com.alejandromejia.hellobuildtest.utils.EMAIL
import com.alejandromejia.hellobuildtest.utils.NAME
import com.alejandromejia.hellobuildtest.utils.PHONE
import com.alejandromejia.hellobuildtest.utils.SORT_EMAIL
import com.alejandromejia.hellobuildtest.utils.SORT_NAME
import com.alejandromejia.hellobuildtest.utils.SORT_PHONE
import com.alejandromejia.hellobuildtest.utils.USER_FILTER

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetUsers(viewModels: ViewModels) {
    val bottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Expanded)


    if (bottomSheetState.currentValue == ModalBottomSheetValue.Hidden) {
        viewModels.mainViewModel.showBottomSheet(false)
    }

    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetContent = {
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(alignment = Alignment.CenterHorizontally)
                        .padding(top = 10.dp)
                ) {
                    Spacer(
                        modifier = Modifier
                            .background(Color.LightGray, CircleShape)
                            .width(35.dp)
                            .height(3.dp)
                            .align(Alignment.Center)
                    )
                }
                Text(
                    text = USER_FILTER,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp, start = 18.dp, end = 16.dp)
                        .align(Alignment.CenterHorizontally)
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 15.dp)
                        .background(
                            color = colorResource(id = R.color.light_Gray_2),
                            shape = RoundedCornerShape(10)
                        )
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp, bottom = 8.dp, start = 16.dp)
                            .noRippleClickable { NoRippleInteractionSource() }
                            .clickable {
                                viewModels.usersViewModel.sortUsers(NAME)
                                viewModels.mainViewModel.showBottomSheet(false)
                            }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                        Text(
                            text = SORT_NAME,
                            fontSize = 14.sp,
                            color = Color.Black,
                            modifier = Modifier.padding(start = 5.dp)
                        )
                    }
                    HorizontalDivider(
                        modifier = Modifier
                            .background(Color.LightGray, RectangleShape)
                            .fillMaxWidth()
                            .height(0.5.dp)
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp, bottom = 8.dp, start = 16.dp)
                            .noRippleClickable { }
                            .clickable {
                                viewModels.usersViewModel.sortUsers(EMAIL)
                                viewModels.mainViewModel.showBottomSheet(false)
                            }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                        Text(
                            text = SORT_EMAIL,
                            fontSize = 14.sp,
                            color = Color.Black,
                            modifier = Modifier.padding(start = 5.dp)
                        )
                    }
                    HorizontalDivider(
                        modifier = Modifier
                            .background(Color.LightGray, RectangleShape)
                            .fillMaxWidth()
                            .height(0.5.dp)
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp, bottom = 8.dp, start = 16.dp)
                            .noRippleClickable { }
                            .clickable {
                                viewModels.usersViewModel.sortUsers(PHONE)
                                viewModels.mainViewModel.showBottomSheet(false)
                            }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Phone,
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                        Text(
                            text = SORT_PHONE,
                            fontSize = 14.sp,
                            color = Color.Black,
                            modifier = Modifier.padding(start = 5.dp)
                        )
                    }
                }
            }

        },
        sheetBackgroundColor = MaterialTheme.colors.background,
        sheetShape = RoundedCornerShape(topEnd = 15.dp, topStart = 15.dp),
        sheetElevation = 8.dp,
    ) {}
}