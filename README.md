Quiz
====
This is a quick solusion for the quiz. I did this in a rush. 

I was told to finish it in one day and half of my time was spent on the drag and drop reordering which I couldn't accomplish. (I still think it's very difficult to have a GridView with different size of cells able to drag and drop though. Possible appoachings are deep hack, using GridLayout instead of GridView or the new RecyclerView. All of them take time.

For the lazy downloading I choose to use Piccaso library. But a better way is to handle the concurrency on your own or use Volley. Still time limit is the issue.

For endless ListView, I simply monitor the scroll event and trigger the LoadMore when it hits certain point.

For picture enlarging, I use the new Activity transition effect to let the user not feel the gap between two Activities. Bugs exist when scrolling too fast. No time to debug.

Simple styling the app. No time to add touch feedback.
