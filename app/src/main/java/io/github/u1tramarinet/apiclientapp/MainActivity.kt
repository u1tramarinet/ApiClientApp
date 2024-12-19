package io.github.u1tramarinet.apiclientapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.u1tramarinet.apiclientapp.domain.github.GetReposMockUseCase
import io.github.u1tramarinet.apiclientapp.domain.github.GetReposUseCase
import io.github.u1tramarinet.apiclientapp.ui.theme.ApiClientAppTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ApiClientAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
        runBlocking(Dispatchers.Default) {
            val repos = GetReposUseCase().invoke()
            repos.forEach { repo ->
                println("repo=${repo.name}")
            }
        }
        runBlocking(Dispatchers.Default) {
            val repos = GetReposMockUseCase().invoke()
            repos.forEach { repo ->
                println("repo(mock)=${repo.name}")
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ApiClientAppTheme {
        Greeting("Android")
    }
}