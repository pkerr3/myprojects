function squareNumber(num){
  temp = num;
  num = num*num;
  console.log("The result of squaring the number " + temp + " is " + num + ".");
  return num;
}
squareNumber(3);

function halfNumber(num){
  temp = num;
  num = num/2;
  console.log("Half of " + temp + " is " + num + ".");
  return num;
}
halfNumber(5);

function percentOf(num1, num2){
  percent = num1/num2 * 100;
  console.log(num1 + " is " + percent + "% of " + num2 + ".");
  return percent;
}
percentOf(2, 4);

function areaOfCircle(radius){
  temp = radius;
  radius = Math.PI * (radius*radius);
  radius = Number((radius).toFixed(2));
  console.log("The area for a circle with radius " + temp + " is " + radius + ".");
  return radius;
}
areaOfCircle(2);

function everything(num){
  result1 = num/2;
  result2 = squareNumber(result1);
  result3 = areaOfCircle(result2);
  result4 = percentOf(result3, result2);
}
everything(10);

var arrayLetters = ['F', 'O', 'X'];
var arrayGuess = [];
for (i = 0; i < arrayLetters.length; i++)
  arrayGuess.push('_');
var isCorrect = false;
var log = '';
var reward = 0;

function guessLetter(letter){
  for (i = 0; i < arrayLetters.length; i++){
    if (letter == arrayLetters[i]){
      arrayGuess[i] = arrayLetters[i];
      isCorrect = true;
    }
  }
  log = log + letter;

  if (isCorrect){
    reward = reward + Math.floor(Math.random() * 1000);
    console.log("Good job! You found a letter! Current Letters: " + log + ". Current reward = $" + reward + ".");
    isCorrect = false;
  }
  else
  {
    reward = reward - Math.floor(Math.random() * 1000);
    console.log("Current Letters: " + log + ". Current reward = $" + reward + ".");
  }

  for (i = 0; i < arrayGuess.length; i++){
    if (arrayGuess[i] == '_')
      break;
    else if (i == arrayGuess.length-1)
      console.log("Congratulations! You've won! Final reward: $" + reward + ".");
  }
}

guessLetter('F');
guessLetter('C');
guessLetter('M');
guessLetter('O');
guessLetter('T');
guessLetter('X');