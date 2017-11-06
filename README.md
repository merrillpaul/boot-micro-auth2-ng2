# Boot1

## PreRequisites
### Java
### Gradle
### Node Version > 6


## Git
* To push use `$ ./gitpush.sh`
This will run tests and ask for the branch name again to push.
* Without tests `$ untestable=true ./gitpush.sh`. Be careful while exercising this option.

* To merge use ./gitmerge.sh
This will ask for the branch to merge into

### Bash Profile for GIT
* copy `tools/git/git-completion.bash` to `~/`
* git terminal info : Open ~/.bash_profile and add the following:
```
RED="\[\033[0;31m\]"
YELLOW="\[\033[0;33m\]"
GREEN="\[\033[0;32m\]"

__git_ps1 ()
{
    local b="$(git symbolic-ref HEAD 2>/dev/null)";
    if [ -n "$b" ]; then
        printf " (%s)" "${b##refs/heads/}";
    fi
}
function parse_git_branch {
  ref=$(git-symbolic-ref HEAD 2> /dev/null) || return
  echo "("${ref#refs/heads/}")"
}

# PS1="\h\$(__git_ps1)$ "
PS1="$RED\$(date +%H:%M) \w$GREEN\e[1m\$(__git_ps1)\e[0m$ "

if [ -f ~/.git-completion.bash ]; then
  . ~/.git-completion.bash
fi
```
