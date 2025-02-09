package com.khan.samacharapp.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import com.khan.samacharapp.R
import com.khan.samacharapp.domain.model.Article
import com.khan.samacharapp.presentation.common.ArticlesList
import com.khan.samacharapp.presentation.common.SearchBar
import com.khan.samacharapp.presentation.navgraph.Route
import com.khan.samacharapp.presentation.onBoarding.Dimesions.MediumPadding1

@Composable
fun HomeScreen(articles:LazyPagingItems<Article>,
               navigateToSearch:()->Unit,
               navigateToDetails:(Article) -> Unit){
    val title by remember {
        derivedStateOf {
            if(articles.itemCount>10){
                articles.itemSnapshotList.items
                    .slice(IntRange(start = 0, endInclusive = 9))
                    .joinToString (separator ="\uD83d\uDFE5" ){it.title}
            }else{
                ""
            }
        }
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(top= MediumPadding1)
        )
    {
        Row{
            Image(painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = null,
                modifier = Modifier.width(75.dp)
                    .height(30.dp)
                    .padding(horizontal = MediumPadding1)
                    .statusBarsPadding()
            )
            Text(text = "SAMACHAR",
                modifier = Modifier.padding(top = 3.dp),
                color = colorResource(id = R.color.placeholder),
                fontStyle = FontStyle.Normal
            )
        }

        Spacer(modifier = Modifier.height(MediumPadding1))

        SearchBar(modifier = Modifier.padding(horizontal = MediumPadding1 ),
            text = "",
            readOnly = true,
            onValueChange = {},
            onClick = {
                navigateToSearch
            },
            onSearch = {}
        )

        Spacer(modifier = Modifier.height(MediumPadding1))

        Text(
            text = title ,
            modifier = Modifier
                .fillMaxWidth()
            .padding(start = MediumPadding1)
            .basicMarquee(),
                fontSize = 12.sp,
            color = colorResource(R.color.placeholder)
        )
        Spacer(modifier = Modifier.height(MediumPadding1))


        ArticlesList(
            modifier = Modifier.padding(horizontal = MediumPadding1),
            articles = articles,
            onCLick = {
                navigateToDetails(it)
            })
    }


}