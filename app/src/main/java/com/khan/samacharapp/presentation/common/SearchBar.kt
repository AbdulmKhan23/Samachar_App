package com.khan.samacharapp.presentation.common

import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.khan.samacharapp.R
import com.khan.samacharapp.presentation.onBoarding.Dimesions.IconSize
import com.khan.samacharapp.ui.theme.SamacharAppTheme

@Composable
fun SearchBar(
    modifier: Modifier=Modifier,
    text:String,
    readOnly:Boolean,
    onClick: (() -> Unit)? =null,
    onValueChange:(String)->Unit,
    onSearch:()->Unit
    )
{
    val interactionSource = remember {
        MutableInteractionSource()
    }
    val isClicked = interactionSource.collectIsPressedAsState().value
    LaunchedEffect(key1=isClicked){
        if(isClicked){
            onClick?.invoke()
        }
    }
    Box (modifier =Modifier){
     TextField(
         modifier = Modifier
             .fillMaxWidth()
             .searchBarBorder(),
         value= text,
         onValueChange = onValueChange,
         readOnly = readOnly,
         leadingIcon = {
             Icon(painter = painterResource(id = R.drawable.ic_search),
                 contentDescription = null,
                 modifier=Modifier.size(IconSize),
                 tint = colorResource(R.color.body)
             )
         },
         placeholder = {
             Text(text = "Search",
                 style = MaterialTheme.typography.bodySmall,
                 color = colorResource(id=R.color.placeholder)
             )
         },
         shape = MaterialTheme.shapes.medium,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = colorResource(R.color.input_background),
            focusedTextColor = if(isSystemInDarkTheme())Color.White else Color.Black,
            cursorColor = if(isSystemInDarkTheme())Color.White else Color.Black,
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
         singleLine = true,
         keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
         keyboardActions = KeyboardActions(
             onSearch = {
                 onSearch()
             }
         ),
         textStyle = MaterialTheme.typography.bodySmall,
         interactionSource = interactionSource
     )

    }

}

fun Modifier.searchBarBorder() = composed{
    if(!isSystemInDarkTheme()){
        border(
            width = 1.dp,
            color = Color.Black,
            shape = MaterialTheme.shapes.medium
        )
    }
    else
    {
        this
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SearchBarPreview(){
    SamacharAppTheme {
        SearchBar(text = "", readOnly = false, onValueChange = {}){

        }
    }
}

