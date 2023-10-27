package ph.edu.uic.quizapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ph.edu.uic.quizapp.ui.theme.QuizAppTheme
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuizAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    QuizApp()
                }
            }
        }
    }
}

data class QuizQuestion(
    val question: String,
    val options: List<String>,
    val correctAnswer: Int
)

@Composable
fun QuizApp() {
    var currentQuestionIndex by remember { mutableStateOf(0) }
    var userScore by remember { mutableStateOf(0) }

    val quizQuestions = listOf(
        QuizQuestion("What is the capital of France?", listOf("Paris", "London", "Berlin"), 0),
        QuizQuestion("Which planet is known as the Red Planet?", listOf("Mars", "Venus", "Jupiter"), 0),
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Display the current question
        Text(
            text = quizQuestions[currentQuestionIndex].question,
            fontSize = 24.sp,
            modifier = Modifier.padding(8.dp)
        )

//        // Display image for the current question
//        Image(
//            painter = painterResource(id = quizQuestions[currentQuestionIndex].imageResourceId),
//            contentDescription = null,
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(200.dp)
//        )

        // Display answer choices
        LazyColumn(
            modifier = Modifier.padding(8.dp)
        ) {
            items(quizQuestions[currentQuestionIndex].options) { option ->
                Button(
                    onClick = {
                        // Handle answer selection here
                        if (option == quizQuestions[currentQuestionIndex].options[quizQuestions[currentQuestionIndex].correctAnswer]) {
                            // Correct answer selected
                            userScore++
                        }
                        // Advance to the next question
                        currentQuestionIndex++
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                ) {
                    Text(text = option)
                }
            }
        }

        // Display user score
        Text(
            text = "Score: $userScore/${quizQuestions.size}",
            fontSize = 18.sp,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    QuizAppTheme {
        QuizApp()
    }
}