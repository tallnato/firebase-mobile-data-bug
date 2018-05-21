# Firebase mobile data bug

I've encountered some weird behavior using Firestore SDK over a mobile data connection in some devices.

In devices like Xiaomi, Asus and Huawei with a mobile internet connection over Vodafone (I'm from Portugal), the application can't connect to the firestore server. Also, it doesn't provide any error messages, like no network for instance , but sometimes it does show the error "client is offline".

Over wifi there is no problem connecting to the server, and using other portuguese mobile internet providers like NOS or MEO or using other devices with Vodafone, there is no problem connecting to the server.

I've tried in the same device, changing the SIM card from Vodafone, not working, to NOS and it works perfectly.


This project shows that error, for those devices and for that mobile data connection.

When the app starts it tries to get data from the server, and instead of returning and error, it returns empty data.

The logs for this error can be found in the [logs.txt](logs.txt)  file.

There is a log in the logs file that says `Firestore: (0.6.6-dev) [OnlineStateTracker]: Could not reach Firestore backend.`

Any suggestions is appreciated on how to solve this.


