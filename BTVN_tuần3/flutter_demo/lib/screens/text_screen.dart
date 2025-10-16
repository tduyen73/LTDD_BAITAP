import 'package:flutter/material.dart';

class TextScreen extends StatelessWidget {
  const TextScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
      appBar: AppBar(
        backgroundColor: Colors.blue,
        foregroundColor: Colors.white,
        title: const Text('Text Detail'),
      ),
      body: const Center(
        child: Padding(
          padding: EdgeInsets.all(20),
          child: Text.rich(
            TextSpan(
              text: 'The ',
              style: TextStyle(fontSize: 22),
              children: [
                TextSpan(
                  text: 'quick ',
                  style: TextStyle(decoration: TextDecoration.lineThrough),
                ),
                TextSpan(
                  text: 'Brown ',
                  style: TextStyle(color: Colors.brown, fontWeight: FontWeight.bold),
                ),
                TextSpan(text: 'fox jumps '),
                TextSpan(
                  text: 'over ',
                  style: TextStyle(fontWeight: FontWeight.bold),
                ),
                TextSpan(
                  text: 'the ',
                  style: TextStyle(decoration: TextDecoration.underline),
                ),
                TextSpan(
                  text: 'lazy dog.',
                  style: TextStyle(fontStyle: FontStyle.italic),
                ),
              ],
            ),
            textAlign: TextAlign.center,
          ),
        ),
      ),
    );
  }
}
