package com.valiantlabs.design_system.system.components


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.valiantlabs.design_system.system.ValiantGrassGreen
import com.valiantlabs.design_system.system.ValiantWalletTheme
import com.valiantlabs.design_system.system.util.ValiantFonts

@Composable
fun ValiantButton(
    text: String,
    isLoading: Boolean = false,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    buttonHeight: Dp,
    buttonWidth: Dp,
    textStyle: TextStyle,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    onClick: () -> Unit,
) {


    Button(
        onClick = onClick,
        enabled = enabled,
        colors = colors,
        shape = RoundedCornerShape(100f),
        modifier = modifier
            .height(buttonHeight)
            .then( // Use then to apply width conditionally
                if (buttonWidth == Dp.Unspecified) {
                    Modifier.fillMaxWidth() // Fill width if unspecified
                } else {
                    Modifier.width(buttonWidth) // Use specific width if provided
                }
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(15.dp)
                    .alpha(if (isLoading) 1f else 0f),
                strokeWidth = 1.5.dp,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Text(
                text = text.uppercase(),
                style = textStyle,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .alpha(if (isLoading) 0f else 1f),
                fontWeight = FontWeight.Bold
            )
        }
    }
}


@PreviewLightDark
@Composable
fun PreviewValiantButtonDark() {
    ValiantWalletTheme {
        ValiantButton(
            colors = ButtonDefaults.buttonColors(
                containerColor = ValiantGrassGreen
            ),
            text = "34343",
            isLoading = false,
            buttonHeight = 40.dp,
            buttonWidth = 90.dp,
            onClick = { /* Your click action */ },
            textStyle = TextStyle(
                fontFamily = ValiantFonts.SpaceGrotesk,
                fontSize = 5.sp
            )
        )
    }
}




