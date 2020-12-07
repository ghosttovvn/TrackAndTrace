#Reflective Report
##Program explanation
- This program is a small command-line program
designed to replicate the functionality of the
Track and Trace system.

- The user should be able to input their
details and the details of an event or establishment
to be later retrieved in the case of a 
coronavirus case found at the 
location.

- The events and establishments are stored in an
array list, to be later retrieved by the program
and displayed or filtered. The user can navigate
the command-line program by inputting numbers into
a text-based menu.

- Records can be filtered by name, date of event, and
the establishment it took place in.

##Thoughts and feelings
- Before beginning this assignment it did seem daunting and
I was unsure of how I would implement it.
The specification made the program a lot clearer though 
since each section was clearly explained.

- However, having a little previous experience 
in Java helped a lot in that I didn't have to constantly
lookup methods and syntax, so I was comfortable in terms
of time and familiarity.

##What went well and what went wrong
- I did spend a fair amount of time
on debugging, and I feel like the code I have
produced is quite messy and resembles spaghetti.

- I didn't pay enough attention to some details
either, such as forgetting to print the return value
of the filters, then wondering why nothing was coming out!

##What I could have done better
- To remedy this, I probably could have written
pseudocode and laid out all the requirements for 
the assignment, beforehand. Instead, I chose to dive straight
in which worked okay in a smaller project like this,
but anything greater would require a lot more
planning.

- I also did not pull my project directly from NU code
and created it myself, so I could have missed something
important.

##If I were to do the problem again, I would have
- I would have liked to have implemented a better way to check for
duplicate events, since EventID is kind of redundant in this
specific test.

- If I were to do the project in more depth, I would check
for errors or incorrect data from a CSV file straight inside 
the controller method rather than the IO.

- I would also implement functionality to check the occupancy
of the establishment at any given time and make sure that too 
many people could not attend at one time.

- I think it would have been better if the program was designed
so that the event date was taken from the user, not added
as the time when the data was input. This would have meant that
a user could retroactively input events or prepare for a future
event.