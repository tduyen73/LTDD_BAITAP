import 'package:flutter/material.dart';

class RowScreen extends StatelessWidget {
  const RowScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
      appBar: AppBar(
        backgroundColor: Colors.blue,
        foregroundColor: Colors.white,
        title: const Text('Row Layout'),
      ),
      body: Padding(
        padding: const EdgeInsets.all(20),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: List.generate(4, (row) {
            return Padding(
              padding: const EdgeInsets.symmetric(vertical: 8),
              child: Row(
                mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                children: List.generate(3, (col) {
                  return Container(
                    width: 80,
                    height: 50,
                    decoration: BoxDecoration(
                      color: Colors.blue[(row + 4) * 100],
                      borderRadius: BorderRadius.circular(8),
                    ),
                  );
                }),
              ),
            );
          }),
        ),
      ),
    );
  }
}
