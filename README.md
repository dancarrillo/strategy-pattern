This is a base implementation of the strategy pattern.  Stay Hungry. Stay Humble.  

# Building the code.  Clean deletes the target dir and wipes out the previous build.  Compile is self-explanatory, and package generates the jar file along with the application's dependencies.
mvn3 clean compile package

# Start/Run the application
# It may take a few seconds to start. It's ready to process requests when you see the message 'Started ServiceApplication in xx.xx seconds' 
java -jar target/strategy_pattern-0.1.0.jar

# Send a request to the web service
#This is the base request for processing the sample blog and loan requests.  Note that the output is piped to python - this is done to pretty print the JSON output.
#You can use either of the sample payloads included at the project root.
curl -H 'Content-Type: application/json' -H 'Accept: application/json' -XPOST -d @blog.json http://localhost:8080/transactions | python -mjson.tool

# Git help
# find what branch I am on, and what has been modified
git status

# Checking in files. Remember that the process for "checking in files" is first add, then commit, then push.
git add --all
or
git add [specific file]

git push

# git branches
# delete a branch
git branch -D [branch name]

# create a branch
git branch [branch name]

# switch to a branch (which you must do after creating one if you want to work on that particular branch
git checkout [branch name] (then use git status to see that you are in fact on that branch)

# set the remote origin so that you will be authorized to push changes to the repo
git remote set-url origin https://dancarrillo@github.com/dancarrillo/strategy-pattern.git
