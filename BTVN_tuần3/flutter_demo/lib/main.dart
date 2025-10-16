import 'package:flutter/material.dart';
import 'screens/home_screen.dart';
import 'screens/text_screen.dart';
import 'screens/image_screen.dart';
import 'screens/textfield_screen.dart';
import 'screens/row_screen.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'UI Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: const HomeScreen(),
      routes: {
        '/text': (context) => const TextScreen(),
        '/image': (context) => const ImageScreen(),
        '/textfield': (context) => const TextFieldScreen(),
        '/row': (context) => const RowScreen(),
      },
    );
  }
}
