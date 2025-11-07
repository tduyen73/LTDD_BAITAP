import 'package:flutter/material.dart';

class ImageScreen extends StatelessWidget {
  const ImageScreen({super.key});

  @override
  Widget build(BuildContext context) {
    const imageUrl =
        'https://upload.wikimedia.org/wikipedia/commons/c/cf/%E1%BA%A2nh_m%E1%BA%B7t_tr%C6%B0%E1%BB%9Bc_%C4%90H_GTVT_TP_HCM.jpg';

    return Scaffold(
      appBar: AppBar(
        title: const Text('Images'),
        centerTitle: true,
        backgroundColor: Colors.blue,
      ),
      body: Padding(
        padding: const EdgeInsets.all(16),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.center,
          children: [
            // Ảnh từ URL
            Image.network(
              imageUrl,
              errorBuilder: (context, error, stackTrace) {
                return const Text(
                  'Không tải được ảnh',
                  style: TextStyle(color: Colors.red),
                );
              },
            ),
            const SizedBox(height: 8),
            Text(
              imageUrl,
              style: const TextStyle(fontSize: 12, color: Colors.blue),
              textAlign: TextAlign.center,
            ),

            const SizedBox(height: 24),

            // Ảnh từ assets
            Image.asset('assets/img_1.jpg'),
            const SizedBox(height: 8),
            const Text('In app'),
          ],
        ),
      ),
    );
  }
}
