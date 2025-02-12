import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState


@Composable
fun CustomAlertDialog(
    yes: () -> Unit,
    no: () -> Unit,
    isOpenDialog: MutableState<Boolean>,
    text: String
) {
    AlertDialog(
        onDismissRequest = {
            isOpenDialog.value = false
        },
        title = {
            Text(text = text)
        },
        text = {

        },
        confirmButton = {
            Button(
                onClick = {
                    yes()
                }
            ) {
                Text("Yes")
            }
        },
        dismissButton = {
            Button(onClick = { no() }) {
                Text("No")
            }
        }
    )
}