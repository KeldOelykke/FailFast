


<!DOCTYPE html>
<html lang="en" class=" is-copy-enabled">
  <head prefix="og: http://ogp.me/ns# fb: http://ogp.me/ns/fb# object: http://ogp.me/ns/object# article: http://ogp.me/ns/article# profile: http://ogp.me/ns/profile#">
    <meta charset='utf-8'>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Content-Language" content="en">
    <meta name="viewport" content="width=1020">
    
    
    <title>checkstyle/README.md at master · checkstyle/checkstyle</title>
    <link rel="search" type="application/opensearchdescription+xml" href="/opensearch.xml" title="GitHub">
    <link rel="fluid-icon" href="https://github.com/fluidicon.png" title="GitHub">
    <link rel="apple-touch-icon" sizes="57x57" href="/apple-touch-icon-114.png">
    <link rel="apple-touch-icon" sizes="114x114" href="/apple-touch-icon-114.png">
    <link rel="apple-touch-icon" sizes="72x72" href="/apple-touch-icon-144.png">
    <link rel="apple-touch-icon" sizes="144x144" href="/apple-touch-icon-144.png">
    <meta property="fb:app_id" content="1401488693436528">

      <meta content="@github" name="twitter:site" /><meta content="summary" name="twitter:card" /><meta content="checkstyle/checkstyle" name="twitter:title" /><meta content="checkstyle - Checkstyle is a development tool to help programmers write Java code that adheres to a coding standard. By default it supports the Google Java Style Guide and Sun Code Conventions, but..." name="twitter:description" /><meta content="https://avatars0.githubusercontent.com/u/5179750?v=3&amp;s=400" name="twitter:image:src" />
      <meta content="GitHub" property="og:site_name" /><meta content="object" property="og:type" /><meta content="https://avatars0.githubusercontent.com/u/5179750?v=3&amp;s=400" property="og:image" /><meta content="checkstyle/checkstyle" property="og:title" /><meta content="https://github.com/checkstyle/checkstyle" property="og:url" /><meta content="checkstyle - Checkstyle is a development tool to help programmers write Java code that adheres to a coding standard. By default it supports the Google Java Style Guide and Sun Code Conventions, but..." property="og:description" />
      <meta name="browser-stats-url" content="https://api.github.com/_private/browser/stats">
    <meta name="browser-errors-url" content="https://api.github.com/_private/browser/errors">
    <link rel="assets" href="https://assets-cdn.github.com/">
    <link rel="web-socket" href="wss://live.github.com/_sockets/NjEzNzEzNToyNDg5Njk4Njc1MDkyZmVkYWU1ZjlmYTQwMTQ2OWUyNzo1ZjMwMGE5NWQwMjY0YjE0YWQ5NjY0NTAxOWE1NzEwYmE5ZmM0Y2QyOGM0M2RkOGE3MjQzZjk4MzFlODMzZDkw--dfe59968b21391e538114a59f354998e166780ee">
    <meta name="pjax-timeout" content="1000">
    <link rel="sudo-modal" href="/sessions/sudo_modal">

    <meta name="msapplication-TileImage" content="/windows-tile.png">
    <meta name="msapplication-TileColor" content="#ffffff">
    <meta name="selected-link" value="repo_source" data-pjax-transient>

        <meta name="google-analytics" content="UA-3769691-2">

    <meta content="collector.githubapp.com" name="octolytics-host" /><meta content="collector-cdn.github.com" name="octolytics-script-host" /><meta content="github" name="octolytics-app-id" /><meta content="535DFF1A:5D15:23E3E2B:55DB6D60" name="octolytics-dimension-request_id" /><meta content="6137135" name="octolytics-actor-id" /><meta content="KeldOelykke" name="octolytics-actor-login" /><meta content="e6644335ba7aec522fc5b02d5d399f748419ac306ebf044486c56904f5386fc5" name="octolytics-actor-hash" />
    
    <meta content="Rails, view, blob#show" data-pjax-transient="true" name="analytics-event" />
    <meta class="js-ga-set" name="dimension1" content="Logged In">
      <meta class="js-ga-set" name="dimension4" content="Current repo nav">
    <meta name="is-dotcom" content="true">
        <meta name="hostname" content="github.com">
    <meta name="user-login" content="KeldOelykke">

      <link rel="icon" sizes="any" mask href="https://assets-cdn.github.com/pinned-octocat.svg">
      <meta name="theme-color" content="#4078c0">
      <link rel="icon" type="image/x-icon" href="https://assets-cdn.github.com/favicon.ico">

    <!-- </textarea> --><!-- '"` --><meta content="authenticity_token" name="csrf-param" />
<meta content="hFJBti8UYstJtpzxtCVezKV+r6QZGkgkNBVjRsI1B2EtUSIJtmhh0ZDWu1qQp43rU5xwxWRN5K+STHwBOZY4gg==" name="csrf-token" />
    

    <link crossorigin="anonymous" href="https://assets-cdn.github.com/assets/github/index-17ad0ea72cb80a46ba6d1bd6e3c69789acb0e1c0cae43beb90477759cce1bdfd.css" media="all" rel="stylesheet" />
    <link crossorigin="anonymous" href="https://assets-cdn.github.com/assets/github2/index-9f11074052a3551cd7bae2fba8b949844d2d7329927a7f1cb5a2c2a821f016e0.css" media="all" rel="stylesheet" />
    
    


    <meta http-equiv="x-pjax-version" content="239d32d0023038613a9092dfdd8544d0">

      
  <meta name="description" content="checkstyle - Checkstyle is a development tool to help programmers write Java code that adheres to a coding standard. By default it supports the Google Java Style Guide and Sun Code Conventions, but is highly configurable. It can be invoked with an ANT task and a command line program.">
  <meta name="go-import" content="github.com/checkstyle/checkstyle git https://github.com/checkstyle/checkstyle.git">

  <meta content="5179750" name="octolytics-dimension-user_id" /><meta content="checkstyle" name="octolytics-dimension-user_login" /><meta content="12499251" name="octolytics-dimension-repository_id" /><meta content="checkstyle/checkstyle" name="octolytics-dimension-repository_nwo" /><meta content="true" name="octolytics-dimension-repository_public" /><meta content="false" name="octolytics-dimension-repository_is_fork" /><meta content="12499251" name="octolytics-dimension-repository_network_root_id" /><meta content="checkstyle/checkstyle" name="octolytics-dimension-repository_network_root_nwo" />
  <link href="https://github.com/checkstyle/checkstyle/commits/master.atom" rel="alternate" title="Recent Commits to checkstyle:master" type="application/atom+xml">

  </head>


  <body class="logged_in  env-production macintosh vis-public page-blob">
    <a href="#start-of-content" tabindex="1" class="accessibility-aid js-skip-to-content">Skip to content</a>
    <div class="wrapper">
      
      
      



        <div class="header header-logged-in true" role="banner">
  <div class="container clearfix">

    <a class="header-logo-invertocat" href="https://github.com/" data-hotkey="g d" aria-label="Homepage" data-ga-click="Header, go to dashboard, icon:logo">
  <span class="mega-octicon octicon-mark-github"></span>
</a>


      <div class="site-search repo-scope js-site-search" role="search">
          <!-- </textarea> --><!-- '"` --><form accept-charset="UTF-8" action="/checkstyle/checkstyle/search" class="js-site-search-form" data-global-search-url="/search" data-repo-search-url="/checkstyle/checkstyle/search" method="get"><div style="margin:0;padding:0;display:inline"><input name="utf8" type="hidden" value="&#x2713;" /></div>
  <label class="js-chromeless-input-container form-control">
    <div class="scope-badge">This repository</div>
    <input type="text"
      class="js-site-search-focus js-site-search-field is-clearable chromeless-input"
      data-hotkey="s"
      name="q"
      placeholder="Search"
      aria-label="Search this repository"
      data-global-scope-placeholder="Search GitHub"
      data-repo-scope-placeholder="Search"
      tabindex="1"
      autocapitalize="off">
  </label>
</form>
      </div>

      <ul class="header-nav left" role="navigation">
        <li class="header-nav-item">
          <a href="/pulls" class="js-selected-navigation-item header-nav-link" data-ga-click="Header, click, Nav menu - item:pulls context:user" data-hotkey="g p" data-selected-links="/pulls /pulls/assigned /pulls/mentioned /pulls">
            Pull requests
</a>        </li>
        <li class="header-nav-item">
          <a href="/issues" class="js-selected-navigation-item header-nav-link" data-ga-click="Header, click, Nav menu - item:issues context:user" data-hotkey="g i" data-selected-links="/issues /issues/assigned /issues/mentioned /issues">
            Issues
</a>        </li>
          <li class="header-nav-item">
            <a class="header-nav-link" href="https://gist.github.com/" data-ga-click="Header, go to gist, text:gist">Gist</a>
          </li>
      </ul>

    
<ul class="header-nav user-nav right" id="user-links">
  <li class="header-nav-item">
      <span class="js-socket-channel js-updatable-content"
        data-channel="notification-changed:KeldOelykke"
        data-url="/notifications/header">
      <a href="/notifications" aria-label="You have no unread notifications" class="header-nav-link notification-indicator tooltipped tooltipped-s" data-ga-click="Header, go to notifications, icon:read" data-hotkey="g n">
          <span class="mail-status all-read"></span>
          <span class="octicon octicon-inbox"></span>
</a>  </span>

  </li>

  <li class="header-nav-item dropdown js-menu-container">
    <a class="header-nav-link tooltipped tooltipped-s js-menu-target" href="/new"
       aria-label="Create new…"
       data-ga-click="Header, create new, icon:add">
      <span class="octicon octicon-plus left"></span>
      <span class="dropdown-caret"></span>
    </a>

    <div class="dropdown-menu-content js-menu-content">
      <ul class="dropdown-menu dropdown-menu-sw">
        
<a class="dropdown-item" href="/new" data-ga-click="Header, create new repository">
  New repository
</a>


  <a class="dropdown-item" href="/organizations/new" data-ga-click="Header, create new organization">
    New organization
  </a>



  <div class="dropdown-divider"></div>
  <div class="dropdown-header">
    <span title="checkstyle/checkstyle">This repository</span>
  </div>
    <a class="dropdown-item" href="/checkstyle/checkstyle/issues/new" data-ga-click="Header, create new issue">
      New issue
    </a>

      </ul>
    </div>
  </li>

  <li class="header-nav-item dropdown js-menu-container">
    <a class="header-nav-link name tooltipped tooltipped-s js-menu-target" href="/KeldOelykke"
       aria-label="View profile and more"
       data-ga-click="Header, show menu, icon:avatar">
      <img alt="@KeldOelykke" class="avatar" height="20" src="https://avatars2.githubusercontent.com/u/6137135?v=3&amp;s=40" width="20" />
      <span class="dropdown-caret"></span>
    </a>

    <div class="dropdown-menu-content js-menu-content">
      <div class="dropdown-menu dropdown-menu-sw">
        <div class="dropdown-header header-nav-current-user css-truncate">
          Signed in as <strong class="css-truncate-target">KeldOelykke</strong>
        </div>
        <div class="dropdown-divider"></div>

        <a class="dropdown-item" href="/KeldOelykke" data-ga-click="Header, go to profile, text:your profile">
          Your profile
        </a>
        <a class="dropdown-item" href="/stars" data-ga-click="Header, go to starred repos, text:your stars">
          Your stars
        </a>
        <a class="dropdown-item" href="/explore" data-ga-click="Header, go to explore, text:explore">
          Explore
        </a>
        <a class="dropdown-item" href="https://help.github.com" data-ga-click="Header, go to help, text:help">
          Help
        </a>
        <div class="dropdown-divider"></div>

        <a class="dropdown-item" href="/settings/profile" data-ga-click="Header, go to settings, icon:settings">
          Settings
        </a>

        <!-- </textarea> --><!-- '"` --><form accept-charset="UTF-8" action="/logout" class="logout-form" data-form-nonce="314566732c3e18dbf5f1d8cf693021a8b8635c9c" method="post"><div style="margin:0;padding:0;display:inline"><input name="utf8" type="hidden" value="&#x2713;" /><input name="authenticity_token" type="hidden" value="sp5bkE76zzle7+l3hwmkOSwTHhrnixZEd383Mx38vhk6rvF2fIC3+ypdc10yUjJBppq0xOL8/rplqxkrNenq5A==" /></div>
          <button class="dropdown-item dropdown-signout" data-ga-click="Header, sign out, icon:logout">
            Sign out
          </button>
</form>      </div>
    </div>
  </li>
</ul>


    
  </div>
</div>

        

        


      <div id="start-of-content" class="accessibility-aid"></div>
          <div class="site" itemscope itemtype="http://schema.org/WebPage">
    <div id="js-flash-container">
      
    </div>
    <div class="pagehead repohead instapaper_ignore readability-menu">
      <div class="container">

        <div class="clearfix">
          
<ul class="pagehead-actions">

  <li>
      <!-- </textarea> --><!-- '"` --><form accept-charset="UTF-8" action="/notifications/subscribe" class="js-social-container" data-autosubmit="true" data-form-nonce="314566732c3e18dbf5f1d8cf693021a8b8635c9c" data-remote="true" method="post"><div style="margin:0;padding:0;display:inline"><input name="utf8" type="hidden" value="&#x2713;" /><input name="authenticity_token" type="hidden" value="n1F0vmGHHExZrN7hChKG7tqHScnpxJwc+atFmTqSrBSELAiJEg1SUK/KMtEG0MDLVfwj11ebrjDd0EJXguD3jw==" /></div>    <input id="repository_id" name="repository_id" type="hidden" value="12499251" />

      <div class="select-menu js-menu-container js-select-menu">
        <a href="/checkstyle/checkstyle/subscription"
          class="btn btn-sm btn-with-count select-menu-button js-menu-target" role="button" tabindex="0" aria-haspopup="true"
          data-ga-click="Repository, click Watch settings, action:blob#show">
          <span class="js-select-button">
            <span class="octicon octicon-eye"></span>
            Watch
          </span>
        </a>
        <a class="social-count js-social-count" href="/checkstyle/checkstyle/watchers">
          57
        </a>

        <div class="select-menu-modal-holder">
          <div class="select-menu-modal subscription-menu-modal js-menu-content" aria-hidden="true">
            <div class="select-menu-header">
              <span class="select-menu-title">Notifications</span>
              <span class="octicon octicon-x js-menu-close" role="button" aria-label="Close"></span>
            </div>

            <div class="select-menu-list js-navigation-container" role="menu">

              <div class="select-menu-item js-navigation-item selected" role="menuitem" tabindex="0">
                <span class="select-menu-item-icon octicon octicon-check"></span>
                <div class="select-menu-item-text">
                  <input checked="checked" id="do_included" name="do" type="radio" value="included" />
                  <span class="select-menu-item-heading">Not watching</span>
                  <span class="description">Be notified when participating or @mentioned.</span>
                  <span class="js-select-button-text hidden-select-button-text">
                    <span class="octicon octicon-eye"></span>
                    Watch
                  </span>
                </div>
              </div>

              <div class="select-menu-item js-navigation-item " role="menuitem" tabindex="0">
                <span class="select-menu-item-icon octicon octicon octicon-check"></span>
                <div class="select-menu-item-text">
                  <input id="do_subscribed" name="do" type="radio" value="subscribed" />
                  <span class="select-menu-item-heading">Watching</span>
                  <span class="description">Be notified of all conversations.</span>
                  <span class="js-select-button-text hidden-select-button-text">
                    <span class="octicon octicon-eye"></span>
                    Unwatch
                  </span>
                </div>
              </div>

              <div class="select-menu-item js-navigation-item " role="menuitem" tabindex="0">
                <span class="select-menu-item-icon octicon octicon-check"></span>
                <div class="select-menu-item-text">
                  <input id="do_ignore" name="do" type="radio" value="ignore" />
                  <span class="select-menu-item-heading">Ignoring</span>
                  <span class="description">Never be notified.</span>
                  <span class="js-select-button-text hidden-select-button-text">
                    <span class="octicon octicon-mute"></span>
                    Stop ignoring
                  </span>
                </div>
              </div>

            </div>

          </div>
        </div>
      </div>
</form>
  </li>

  <li>
    
  <div class="js-toggler-container js-social-container starring-container ">

    <!-- </textarea> --><!-- '"` --><form accept-charset="UTF-8" action="/checkstyle/checkstyle/unstar" class="js-toggler-form starred js-unstar-button" data-form-nonce="314566732c3e18dbf5f1d8cf693021a8b8635c9c" data-remote="true" method="post"><div style="margin:0;padding:0;display:inline"><input name="utf8" type="hidden" value="&#x2713;" /><input name="authenticity_token" type="hidden" value="HNZiRkJkqmnzSAIO2IX9svaJdNvxYJg0gTwCtCfED8e9E8otIDA5t3PegiYMDBMjwKTg5e7bAmhvlr7GRnDp7g==" /></div>
      <button
        class="btn btn-sm btn-with-count js-toggler-target"
        aria-label="Unstar this repository" title="Unstar checkstyle/checkstyle"
        data-ga-click="Repository, click unstar button, action:blob#show; text:Unstar">
        <span class="octicon octicon-star"></span>
        Unstar
      </button>
        <a class="social-count js-social-count" href="/checkstyle/checkstyle/stargazers">
          467
        </a>
</form>
    <!-- </textarea> --><!-- '"` --><form accept-charset="UTF-8" action="/checkstyle/checkstyle/star" class="js-toggler-form unstarred js-star-button" data-form-nonce="314566732c3e18dbf5f1d8cf693021a8b8635c9c" data-remote="true" method="post"><div style="margin:0;padding:0;display:inline"><input name="utf8" type="hidden" value="&#x2713;" /><input name="authenticity_token" type="hidden" value="nUMGUvaEzAz60zETHaWwzZrsbVr5mHpXja0nqCbraGSg8qZM3LiQAADb+JrIe42YOl5DPbiTqX4C1n+m/DpEtA==" /></div>
      <button
        class="btn btn-sm btn-with-count js-toggler-target"
        aria-label="Star this repository" title="Star checkstyle/checkstyle"
        data-ga-click="Repository, click star button, action:blob#show; text:Star">
        <span class="octicon octicon-star"></span>
        Star
      </button>
        <a class="social-count js-social-count" href="/checkstyle/checkstyle/stargazers">
          467
        </a>
</form>  </div>

  </li>

        <li>
          <a href="#fork-destination-box" class="btn btn-sm btn-with-count"
              title="Fork your own copy of checkstyle/checkstyle to your account"
              aria-label="Fork your own copy of checkstyle/checkstyle to your account"
              rel="facebox"
              data-ga-click="Repository, show fork modal, action:blob#show; text:Fork">
            <span class="octicon octicon-repo-forked"></span>
            Fork
          </a>
          <a href="/checkstyle/checkstyle/network" class="social-count">253</a>

          <div id="fork-destination-box" style="display: none;">
            <h2 class="facebox-header" id="facebox-header">Where should we fork this repository?</h2>
            <include-fragment src=""
                class="js-fork-select-fragment fork-select-fragment"
                data-url="/checkstyle/checkstyle/fork?fragment=1">
              <img alt="Loading" height="64" src="https://assets-cdn.github.com/images/spinners/octocat-spinner-128.gif" width="64" />
            </include-fragment>
          </div>
        </li>

</ul>

          <h1 itemscope itemtype="http://data-vocabulary.org/Breadcrumb" class="entry-title public ">
  <span class="mega-octicon octicon-repo"></span>
  <span class="author"><a href="/checkstyle" class="url fn" itemprop="url" rel="author"><span itemprop="title">checkstyle</span></a></span><!--
--><span class="path-divider">/</span><!--
--><strong><a href="/checkstyle/checkstyle" data-pjax="#js-repo-pjax-container">checkstyle</a></strong>

  <span class="page-context-loader">
    <img alt="" height="16" src="https://assets-cdn.github.com/images/spinners/octocat-spinner-32.gif" width="16" />
  </span>

</h1>
        
        </div>
      </div>
    </div>

    <div class="container">
      <div class="repository-with-sidebar repo-container new-discussion-timeline ">
        <div class="repository-sidebar clearfix">
          
<nav class="sunken-menu repo-nav js-repo-nav js-sidenav-container-pjax js-octicon-loaders"
     role="navigation"
     data-pjax="#js-repo-pjax-container"
     data-issue-count-url="/checkstyle/checkstyle/issues/counts">
  <ul class="sunken-menu-group">
    <li class="tooltipped tooltipped-w" aria-label="Code">
      <a href="/checkstyle/checkstyle" aria-label="Code" aria-selected="true" class="js-selected-navigation-item selected sunken-menu-item" data-hotkey="g c" data-selected-links="repo_source repo_downloads repo_commits repo_releases repo_tags repo_branches /checkstyle/checkstyle">
        <span class="octicon octicon-code"></span> <span class="full-word">Code</span>
        <img alt="" class="mini-loader" height="16" src="https://assets-cdn.github.com/images/spinners/octocat-spinner-32.gif" width="16" />
</a>    </li>

      <li class="tooltipped tooltipped-w" aria-label="Issues">
        <a href="/checkstyle/checkstyle/issues" aria-label="Issues" class="js-selected-navigation-item sunken-menu-item" data-hotkey="g i" data-selected-links="repo_issues repo_labels repo_milestones /checkstyle/checkstyle/issues">
          <span class="octicon octicon-issue-opened"></span> <span class="full-word">Issues</span>
          <span class="js-issue-replace-counter"></span>
          <img alt="" class="mini-loader" height="16" src="https://assets-cdn.github.com/images/spinners/octocat-spinner-32.gif" width="16" />
</a>      </li>

    <li class="tooltipped tooltipped-w" aria-label="Pull requests">
      <a href="/checkstyle/checkstyle/pulls" aria-label="Pull requests" class="js-selected-navigation-item sunken-menu-item" data-hotkey="g p" data-selected-links="repo_pulls /checkstyle/checkstyle/pulls">
          <span class="octicon octicon-git-pull-request"></span> <span class="full-word">Pull requests</span>
          <span class="js-pull-replace-counter"></span>
          <img alt="" class="mini-loader" height="16" src="https://assets-cdn.github.com/images/spinners/octocat-spinner-32.gif" width="16" />
</a>    </li>

      <li class="tooltipped tooltipped-w" aria-label="Wiki">
        <a href="/checkstyle/checkstyle/wiki" aria-label="Wiki" class="js-selected-navigation-item sunken-menu-item" data-hotkey="g w" data-selected-links="repo_wiki /checkstyle/checkstyle/wiki">
          <span class="octicon octicon-book"></span> <span class="full-word">Wiki</span>
          <img alt="" class="mini-loader" height="16" src="https://assets-cdn.github.com/images/spinners/octocat-spinner-32.gif" width="16" />
</a>      </li>
  </ul>
  <div class="sunken-menu-separator"></div>
  <ul class="sunken-menu-group">

    <li class="tooltipped tooltipped-w" aria-label="Pulse">
      <a href="/checkstyle/checkstyle/pulse" aria-label="Pulse" class="js-selected-navigation-item sunken-menu-item" data-selected-links="pulse /checkstyle/checkstyle/pulse">
        <span class="octicon octicon-pulse"></span> <span class="full-word">Pulse</span>
        <img alt="" class="mini-loader" height="16" src="https://assets-cdn.github.com/images/spinners/octocat-spinner-32.gif" width="16" />
</a>    </li>

    <li class="tooltipped tooltipped-w" aria-label="Graphs">
      <a href="/checkstyle/checkstyle/graphs" aria-label="Graphs" class="js-selected-navigation-item sunken-menu-item" data-selected-links="repo_graphs repo_contributors /checkstyle/checkstyle/graphs">
        <span class="octicon octicon-graph"></span> <span class="full-word">Graphs</span>
        <img alt="" class="mini-loader" height="16" src="https://assets-cdn.github.com/images/spinners/octocat-spinner-32.gif" width="16" />
</a>    </li>
  </ul>


</nav>

            <div class="only-with-full-nav">
                
<div class="js-clone-url clone-url open"
  data-protocol-type="http">
  <h3><span class="text-emphasized">HTTPS</span> clone URL</h3>
  <div class="input-group js-zeroclipboard-container">
    <input type="text" class="input-mini input-monospace js-url-field js-zeroclipboard-target"
           value="https://github.com/checkstyle/checkstyle.git" readonly="readonly" aria-label="HTTPS clone URL">
    <span class="input-group-button">
      <button aria-label="Copy to clipboard" class="js-zeroclipboard btn btn-sm zeroclipboard-button tooltipped tooltipped-s" data-copied-hint="Copied!" type="button"><span class="octicon octicon-clippy"></span></button>
    </span>
  </div>
</div>

  
<div class="js-clone-url clone-url "
  data-protocol-type="ssh">
  <h3><span class="text-emphasized">SSH</span> clone URL</h3>
  <div class="input-group js-zeroclipboard-container">
    <input type="text" class="input-mini input-monospace js-url-field js-zeroclipboard-target"
           value="git@github.com:checkstyle/checkstyle.git" readonly="readonly" aria-label="SSH clone URL">
    <span class="input-group-button">
      <button aria-label="Copy to clipboard" class="js-zeroclipboard btn btn-sm zeroclipboard-button tooltipped tooltipped-s" data-copied-hint="Copied!" type="button"><span class="octicon octicon-clippy"></span></button>
    </span>
  </div>
</div>

  
<div class="js-clone-url clone-url "
  data-protocol-type="subversion">
  <h3><span class="text-emphasized">Subversion</span> checkout URL</h3>
  <div class="input-group js-zeroclipboard-container">
    <input type="text" class="input-mini input-monospace js-url-field js-zeroclipboard-target"
           value="https://github.com/checkstyle/checkstyle" readonly="readonly" aria-label="Subversion checkout URL">
    <span class="input-group-button">
      <button aria-label="Copy to clipboard" class="js-zeroclipboard btn btn-sm zeroclipboard-button tooltipped tooltipped-s" data-copied-hint="Copied!" type="button"><span class="octicon octicon-clippy"></span></button>
    </span>
  </div>
</div>



  <div class="clone-options">You can clone with
    <!-- </textarea> --><!-- '"` --><form accept-charset="UTF-8" action="/users/set_protocol?protocol_selector=http&amp;protocol_type=clone" class="inline-form js-clone-selector-form is-enabled" data-form-nonce="314566732c3e18dbf5f1d8cf693021a8b8635c9c" data-remote="true" method="post"><div style="margin:0;padding:0;display:inline"><input name="utf8" type="hidden" value="&#x2713;" /><input name="authenticity_token" type="hidden" value="Hvf31VukvyExcpelLos0PSKOmpDKpjiz7aF4jJxyyCL8xaw7AiBKVQEPesu/iuwhE8ab0uNZEIJ58h5yb+DdsA==" /></div><button class="btn-link js-clone-selector" data-protocol="http" type="submit">HTTPS</button></form>, <!-- </textarea> --><!-- '"` --><form accept-charset="UTF-8" action="/users/set_protocol?protocol_selector=ssh&amp;protocol_type=clone" class="inline-form js-clone-selector-form is-enabled" data-form-nonce="314566732c3e18dbf5f1d8cf693021a8b8635c9c" data-remote="true" method="post"><div style="margin:0;padding:0;display:inline"><input name="utf8" type="hidden" value="&#x2713;" /><input name="authenticity_token" type="hidden" value="6xTxGsabmdG1hZtbJsQ4ONdBZgGKIRyazYecpVNb6LvSkyGrptCCf5JXylW4k76H264ATqURqyTzTPDI11z39g==" /></div><button class="btn-link js-clone-selector" data-protocol="ssh" type="submit">SSH</button></form>, or <!-- </textarea> --><!-- '"` --><form accept-charset="UTF-8" action="/users/set_protocol?protocol_selector=subversion&amp;protocol_type=clone" class="inline-form js-clone-selector-form is-enabled" data-form-nonce="314566732c3e18dbf5f1d8cf693021a8b8635c9c" data-remote="true" method="post"><div style="margin:0;padding:0;display:inline"><input name="utf8" type="hidden" value="&#x2713;" /><input name="authenticity_token" type="hidden" value="GZi5FIVRQK8JRDIw1TZE/uNmTtKa9suvz+NqWLcJ3+wl7QRmOufGD6i7V6k2nIEcksNqfd15kkbWzQP2j3p8QQ==" /></div><button class="btn-link js-clone-selector" data-protocol="subversion" type="submit">Subversion</button></form>.
    <a href="https://help.github.com/articles/which-remote-url-should-i-use" class="help tooltipped tooltipped-n" aria-label="Get help on which URL is right for you.">
      <span class="octicon octicon-question"></span>
    </a>
  </div>
    <a href="github-mac://openRepo/https://github.com/checkstyle/checkstyle" class="btn btn-sm sidebar-button" title="Save checkstyle/checkstyle to your computer and use it in GitHub Desktop." aria-label="Save checkstyle/checkstyle to your computer and use it in GitHub Desktop.">
      <span class="octicon octicon-desktop-download"></span>
      Clone in Desktop
    </a>

              <a href="/checkstyle/checkstyle/archive/master.zip"
                 class="btn btn-sm sidebar-button"
                 aria-label="Download the contents of checkstyle/checkstyle as a zip file"
                 title="Download the contents of checkstyle/checkstyle as a zip file"
                 rel="nofollow">
                <span class="octicon octicon-cloud-download"></span>
                Download ZIP
              </a>
            </div>
        </div>
        <div id="js-repo-pjax-container" class="repository-content context-loader-container" data-pjax-container>

          

<a href="/checkstyle/checkstyle/blob/abfc952fd0ed88f6246b1d969a8a980ea63f0bd6/README.md" class="hidden js-permalink-shortcut" data-hotkey="y">Permalink</a>

<!-- blob contrib key: blob_contributors:v21:ea7672296b80a2168c50f5b2418b1bd5 -->

  <div class="file-navigation js-zeroclipboard-container">
    
<div class="select-menu js-menu-container js-select-menu left">
  <span class="btn btn-sm select-menu-button js-menu-target css-truncate" data-hotkey="w"
    data-ref="master"
    title="master"
    role="button" aria-label="Switch branches or tags" tabindex="0" aria-haspopup="true">
    <i>Branch:</i>
    <span class="js-select-button css-truncate-target">master</span>
  </span>

  <div class="select-menu-modal-holder js-menu-content js-navigation-container" data-pjax aria-hidden="true">

    <div class="select-menu-modal">
      <div class="select-menu-header">
        <span class="select-menu-title">Switch branches/tags</span>
        <span class="octicon octicon-x js-menu-close" role="button" aria-label="Close"></span>
      </div>

      <div class="select-menu-filters">
        <div class="select-menu-text-filter">
          <input type="text" aria-label="Filter branches/tags" id="context-commitish-filter-field" class="js-filterable-field js-navigation-enable" placeholder="Filter branches/tags">
        </div>
        <div class="select-menu-tabs">
          <ul>
            <li class="select-menu-tab">
              <a href="#" data-tab-filter="branches" data-filter-placeholder="Filter branches/tags" class="js-select-menu-tab" role="tab">Branches</a>
            </li>
            <li class="select-menu-tab">
              <a href="#" data-tab-filter="tags" data-filter-placeholder="Find a tag…" class="js-select-menu-tab" role="tab">Tags</a>
            </li>
          </ul>
        </div>
      </div>

      <div class="select-menu-list select-menu-tab-bucket js-select-menu-tab-bucket" data-tab-filter="branches" role="menu">

        <div data-filterable-for="context-commitish-filter-field" data-filterable-type="substring">


            <a class="select-menu-item js-navigation-item js-navigation-open "
               href="/checkstyle/checkstyle/blob/i1294-PropertyCacheFileTest/README.md"
               data-name="i1294-PropertyCacheFileTest"
               data-skip-pjax="true"
               rel="nofollow">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <span class="select-menu-item-text css-truncate-target" title="i1294-PropertyCacheFileTest">
                i1294-PropertyCacheFileTest
              </span>
            </a>
            <a class="select-menu-item js-navigation-item js-navigation-open selected"
               href="/checkstyle/checkstyle/blob/master/README.md"
               data-name="master"
               data-skip-pjax="true"
               rel="nofollow">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <span class="select-menu-item-text css-truncate-target" title="master">
                master
              </span>
            </a>
            <a class="select-menu-item js-navigation-item js-navigation-open "
               href="/checkstyle/checkstyle/blob/master-hotfix-6.8/README.md"
               data-name="master-hotfix-6.8"
               data-skip-pjax="true"
               rel="nofollow">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <span class="select-menu-item-text css-truncate-target" title="master-hotfix-6.8">
                master-hotfix-6.8
              </span>
            </a>
        </div>

          <div class="select-menu-no-results">Nothing to show</div>
      </div>

      <div class="select-menu-list select-menu-tab-bucket js-select-menu-tab-bucket" data-tab-filter="tags">
        <div data-filterable-for="context-commitish-filter-field" data-filterable-type="substring">


            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/checkstyle/checkstyle/tree/v2-branch_lmp/README.md"
                 data-name="v2-branch_lmp"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="v2-branch_lmp">v2-branch_lmp</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/checkstyle/checkstyle/tree/release5_7/README.md"
                 data-name="release5_7"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="release5_7">release5_7</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/checkstyle/checkstyle/tree/release5_6/README.md"
                 data-name="release5_6"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="release5_6">release5_6</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/checkstyle/checkstyle/tree/release5_5/README.md"
                 data-name="release5_5"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="release5_5">release5_5</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/checkstyle/checkstyle/tree/release5_4/README.md"
                 data-name="release5_4"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="release5_4">release5_4</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/checkstyle/checkstyle/tree/release5_3/README.md"
                 data-name="release5_3"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="release5_3">release5_3</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/checkstyle/checkstyle/tree/release4_4/README.md"
                 data-name="release4_4"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="release4_4">release4_4</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/checkstyle/checkstyle/tree/release4_3/README.md"
                 data-name="release4_3"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="release4_3">release4_3</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/checkstyle/checkstyle/tree/release4_2/README.md"
                 data-name="release4_2"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="release4_2">release4_2</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/checkstyle/checkstyle/tree/release4_1/README.md"
                 data-name="release4_1"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="release4_1">release4_1</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/checkstyle/checkstyle/tree/release4_0_beta_5/README.md"
                 data-name="release4_0_beta_5"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="release4_0_beta_5">release4_0_beta_5</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/checkstyle/checkstyle/tree/release4_0_beta_4/README.md"
                 data-name="release4_0_beta_4"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="release4_0_beta_4">release4_0_beta_4</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/checkstyle/checkstyle/tree/release4_0_beta_3/README.md"
                 data-name="release4_0_beta_3"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="release4_0_beta_3">release4_0_beta_3</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/checkstyle/checkstyle/tree/release4_0_beta_2/README.md"
                 data-name="release4_0_beta_2"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="release4_0_beta_2">release4_0_beta_2</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/checkstyle/checkstyle/tree/release4_0_beta_1/README.md"
                 data-name="release4_0_beta_1"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="release4_0_beta_1">release4_0_beta_1</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/checkstyle/checkstyle/tree/release4_0/README.md"
                 data-name="release4_0"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="release4_0">release4_0</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/checkstyle/checkstyle/tree/release3_5/README.md"
                 data-name="release3_5"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="release3_5">release3_5</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/checkstyle/checkstyle/tree/release3_4/README.md"
                 data-name="release3_4"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="release3_4">release3_4</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/checkstyle/checkstyle/tree/release3_3/README.md"
                 data-name="release3_3"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="release3_3">release3_3</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/checkstyle/checkstyle/tree/release3_2/README.md"
                 data-name="release3_2"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="release3_2">release3_2</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/checkstyle/checkstyle/tree/release3_1/README.md"
                 data-name="release3_1"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="release3_1">release3_1</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/checkstyle/checkstyle/tree/release3_0/README.md"
                 data-name="release3_0"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="release3_0">release3_0</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/checkstyle/checkstyle/tree/release2_4/README.md"
                 data-name="release2_4"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="release2_4">release2_4</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/checkstyle/checkstyle/tree/release2_2/README.md"
                 data-name="release2_2"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="release2_2">release2_2</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/checkstyle/checkstyle/tree/release2_0/README.md"
                 data-name="release2_0"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="release2_0">release2_0</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/checkstyle/checkstyle/tree/release1_4/README.md"
                 data-name="release1_4"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="release1_4">release1_4</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/checkstyle/checkstyle/tree/release1_3/README.md"
                 data-name="release1_3"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="release1_3">release1_3</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/checkstyle/checkstyle/tree/release1_2/README.md"
                 data-name="release1_2"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="release1_2">release1_2</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/checkstyle/checkstyle/tree/release1_1/README.md"
                 data-name="release1_1"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="release1_1">release1_1</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/checkstyle/checkstyle/tree/checkstyle-6.9/README.md"
                 data-name="checkstyle-6.9"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="checkstyle-6.9">checkstyle-6.9</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/checkstyle/checkstyle/tree/checkstyle-6.8.2/README.md"
                 data-name="checkstyle-6.8.2"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="checkstyle-6.8.2">checkstyle-6.8.2</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/checkstyle/checkstyle/tree/checkstyle-6.8.1/README.md"
                 data-name="checkstyle-6.8.1"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="checkstyle-6.8.1">checkstyle-6.8.1</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/checkstyle/checkstyle/tree/checkstyle-6.8/README.md"
                 data-name="checkstyle-6.8"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="checkstyle-6.8">checkstyle-6.8</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/checkstyle/checkstyle/tree/checkstyle-6.7/README.md"
                 data-name="checkstyle-6.7"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="checkstyle-6.7">checkstyle-6.7</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/checkstyle/checkstyle/tree/checkstyle-6.6/README.md"
                 data-name="checkstyle-6.6"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="checkstyle-6.6">checkstyle-6.6</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/checkstyle/checkstyle/tree/checkstyle-6.5/README.md"
                 data-name="checkstyle-6.5"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="checkstyle-6.5">checkstyle-6.5</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/checkstyle/checkstyle/tree/checkstyle-6.4.1/README.md"
                 data-name="checkstyle-6.4.1"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="checkstyle-6.4.1">checkstyle-6.4.1</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/checkstyle/checkstyle/tree/checkstyle-6.4/README.md"
                 data-name="checkstyle-6.4"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="checkstyle-6.4">checkstyle-6.4</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/checkstyle/checkstyle/tree/checkstyle-6.3/README.md"
                 data-name="checkstyle-6.3"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="checkstyle-6.3">checkstyle-6.3</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/checkstyle/checkstyle/tree/checkstyle-6.2/README.md"
                 data-name="checkstyle-6.2"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="checkstyle-6.2">checkstyle-6.2</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/checkstyle/checkstyle/tree/checkstyle-6.1.1/README.md"
                 data-name="checkstyle-6.1.1"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="checkstyle-6.1.1">checkstyle-6.1.1</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/checkstyle/checkstyle/tree/checkstyle-6.1/README.md"
                 data-name="checkstyle-6.1"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="checkstyle-6.1">checkstyle-6.1</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/checkstyle/checkstyle/tree/checkstyle-6.0/README.md"
                 data-name="checkstyle-6.0"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="checkstyle-6.0">checkstyle-6.0</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/checkstyle/checkstyle/tree/checkstyle-5.9/README.md"
                 data-name="checkstyle-5.9"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="checkstyle-5.9">checkstyle-5.9</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/checkstyle/checkstyle/tree/checkstyle-5.8/README.md"
                 data-name="checkstyle-5.8"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="checkstyle-5.8">checkstyle-5.8</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/checkstyle/checkstyle/tree/bcel/README.md"
                 data-name="bcel"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="bcel">bcel</a>
            </div>
        </div>

        <div class="select-menu-no-results">Nothing to show</div>
      </div>

    </div>
  </div>
</div>

    <div class="btn-group right">
      <a href="/checkstyle/checkstyle/find/master"
            class="js-show-file-finder btn btn-sm empty-icon tooltipped tooltipped-nw"
            data-pjax
            data-hotkey="t"
            aria-label="Quickly jump between files">
        <span class="octicon octicon-list-unordered"></span>
      </a>
      <button aria-label="Copy file path to clipboard" class="js-zeroclipboard btn btn-sm zeroclipboard-button tooltipped tooltipped-s" data-copied-hint="Copied!" type="button"><span class="octicon octicon-clippy"></span></button>
    </div>

    <div class="breadcrumb js-zeroclipboard-target">
      <span class="repo-root js-repo-root"><span itemscope="" itemtype="http://data-vocabulary.org/Breadcrumb"><a href="/checkstyle/checkstyle" class="" data-branch="master" data-pjax="true" itemscope="url"><span itemprop="title">checkstyle</span></a></span></span><span class="separator">/</span><strong class="final-path">README.md</strong>
    </div>
  </div>


  <div class="commit file-history-tease">
    <div class="file-history-tease-header">
        <img alt="@MEZk" class="avatar" height="24" src="https://avatars2.githubusercontent.com/u/7242568?v=3&amp;s=48" width="24" />
        <span class="author"><a href="/MEZk" rel="contributor">MEZk</a></span>
        <time datetime="2015-08-09T15:49:19Z" is="relative-time">Aug 9, 2015</time>
        <div class="commit-title">
            <a href="/checkstyle/checkstyle/commit/d0939d3bc93819ebc1f42642806c0265fc7908b3" class="message" data-pjax="true" title="Corrected links on website, issue #751">Corrected links on website, issue</a> <a href="https://github.com/checkstyle/checkstyle/issues/751" class="issue-link" title="Not all links on website are working">#751</a>
        </div>
    </div>

    <div class="participation">
      <p class="quickstat">
        <a href="#blob_contributors_box" rel="facebox">
          <strong>6</strong>
           contributors
        </a>
      </p>
          <a class="avatar-link tooltipped tooltipped-s" aria-label="romani" href="/checkstyle/checkstyle/commits/master/README.md?author=romani"><img alt="@romani" class="avatar" height="20" src="https://avatars0.githubusercontent.com/u/812984?v=3&amp;s=40" width="20" /> </a>
    <a class="avatar-link tooltipped tooltipped-s" aria-label="mkordas" href="/checkstyle/checkstyle/commits/master/README.md?author=mkordas"><img alt="@mkordas" class="avatar" height="20" src="https://avatars3.githubusercontent.com/u/5467276?v=3&amp;s=40" width="20" /> </a>
    <a class="avatar-link tooltipped tooltipped-s" aria-label="TwoStone" href="/checkstyle/checkstyle/commits/master/README.md?author=TwoStone"><img alt="@TwoStone" class="avatar" height="20" src="https://avatars3.githubusercontent.com/u/836228?v=3&amp;s=40" width="20" /> </a>
    <a class="avatar-link tooltipped tooltipped-s" aria-label="alexkravin" href="/checkstyle/checkstyle/commits/master/README.md?author=alexkravin"><img alt="@alexkravin" class="avatar" height="20" src="https://avatars1.githubusercontent.com/u/6840443?v=3&amp;s=40" width="20" /> </a>
    <a class="avatar-link tooltipped tooltipped-s" aria-label="AnirvanSarkar" href="/checkstyle/checkstyle/commits/master/README.md?author=AnirvanSarkar"><img alt="@AnirvanSarkar" class="avatar" height="20" src="https://avatars3.githubusercontent.com/u/3539501?v=3&amp;s=40" width="20" /> </a>
    <a class="avatar-link tooltipped tooltipped-s" aria-label="MEZk" href="/checkstyle/checkstyle/commits/master/README.md?author=MEZk"><img alt="@MEZk" class="avatar" height="20" src="https://avatars0.githubusercontent.com/u/7242568?v=3&amp;s=40" width="20" /> </a>


    </div>
    <div id="blob_contributors_box" style="display:none">
      <h2 class="facebox-header">Users who have contributed to this file</h2>
      <ul class="facebox-user-list">
          <li class="facebox-user-list-item">
            <img alt="@romani" height="24" src="https://avatars2.githubusercontent.com/u/812984?v=3&amp;s=48" width="24" />
            <a href="/romani">romani</a>
          </li>
          <li class="facebox-user-list-item">
            <img alt="@mkordas" height="24" src="https://avatars1.githubusercontent.com/u/5467276?v=3&amp;s=48" width="24" />
            <a href="/mkordas">mkordas</a>
          </li>
          <li class="facebox-user-list-item">
            <img alt="@TwoStone" height="24" src="https://avatars1.githubusercontent.com/u/836228?v=3&amp;s=48" width="24" />
            <a href="/TwoStone">TwoStone</a>
          </li>
          <li class="facebox-user-list-item">
            <img alt="@alexkravin" height="24" src="https://avatars3.githubusercontent.com/u/6840443?v=3&amp;s=48" width="24" />
            <a href="/alexkravin">alexkravin</a>
          </li>
          <li class="facebox-user-list-item">
            <img alt="@AnirvanSarkar" height="24" src="https://avatars1.githubusercontent.com/u/3539501?v=3&amp;s=48" width="24" />
            <a href="/AnirvanSarkar">AnirvanSarkar</a>
          </li>
          <li class="facebox-user-list-item">
            <img alt="@MEZk" height="24" src="https://avatars2.githubusercontent.com/u/7242568?v=3&amp;s=48" width="24" />
            <a href="/MEZk">MEZk</a>
          </li>
      </ul>
    </div>
  </div>

<div class="file">
  <div class="file-header">
    <div class="file-actions">

      <div class="btn-group">
        <a href="/checkstyle/checkstyle/raw/master/README.md" class="btn btn-sm " id="raw-url">Raw</a>
          <a href="/checkstyle/checkstyle/blame/master/README.md" class="btn btn-sm js-update-url-with-hash">Blame</a>
        <a href="/checkstyle/checkstyle/commits/master/README.md" class="btn btn-sm " rel="nofollow">History</a>
      </div>

        <a class="octicon-btn tooltipped tooltipped-nw"
           href="github-mac://openRepo/https://github.com/checkstyle/checkstyle?branch=master&amp;filepath=README.md"
           aria-label="Open this file in GitHub Desktop"
           data-ga-click="Repository, open with desktop, type:mac">
            <span class="octicon octicon-device-desktop"></span>
        </a>

            <!-- </textarea> --><!-- '"` --><form accept-charset="UTF-8" action="/checkstyle/checkstyle/edit/master/README.md" class="inline-form" data-form-nonce="314566732c3e18dbf5f1d8cf693021a8b8635c9c" method="post"><div style="margin:0;padding:0;display:inline"><input name="utf8" type="hidden" value="&#x2713;" /><input name="authenticity_token" type="hidden" value="cCQ2TTEcgMJIwp3tqNfvv80rQNOguNGUU9nNq2+vQpU/2vDhWh9F3zCGibNaOAx+Az1E/kEMTElt9W8CKyPHFw==" /></div>
              <button class="octicon-btn tooltipped tooltipped-n" type="submit" aria-label="Fork this project and edit the file" data-hotkey="e" data-disable-with>
                <span class="octicon octicon-pencil"></span>
              </button>
</form>
          <!-- </textarea> --><!-- '"` --><form accept-charset="UTF-8" action="/checkstyle/checkstyle/delete/master/README.md" class="inline-form" data-form-nonce="314566732c3e18dbf5f1d8cf693021a8b8635c9c" method="post"><div style="margin:0;padding:0;display:inline"><input name="utf8" type="hidden" value="&#x2713;" /><input name="authenticity_token" type="hidden" value="BJ+qP+xLAZ2FKMIPskof77gVCk2u2rFnWxnvr+JGbZrN7f6LTGi2BGKaReHU3UubdAs6cQr64MMqndMxqWJSFQ==" /></div>
            <button class="octicon-btn octicon-btn-danger tooltipped tooltipped-n" type="submit" aria-label="Fork this project and delete this file" data-disable-with>
              <span class="octicon octicon-trashcan"></span>
            </button>
</form>    </div>

    <div class="file-info">
        90 lines (60 sloc)
        <span class="file-info-divider"></span>
      3.736 kB
    </div>
  </div>
  
  <div id="readme" class="blob instapaper_body">
    <article class="markdown-body entry-content" itemprop="mainContentOfPage"><p><a href="http://travis-ci.org/checkstyle/checkstyle"><img src="https://camo.githubusercontent.com/a2dd0c8c783478db0e6d87e58a2b6d0c050b738c/68747470733a2f2f7365637572652e7472617669732d63692e6f72672f636865636b7374796c652f636865636b7374796c652e706e67" alt="" data-canonical-src="https://secure.travis-ci.org/checkstyle/checkstyle.png" style="max-width:100%;"></a>
<a href="https://ci.appveyor.com/project/checkstyle/checkstyle"><img src="https://camo.githubusercontent.com/05ff7560846e3a37cd8c779723e16af51de02dd2/68747470733a2f2f63692e6170707665796f722e636f6d2f6170692f70726f6a656374732f7374617475732f727736627733646c396b7068367563633f7376673d74727565" alt="" data-canonical-src="https://ci.appveyor.com/api/projects/status/rw6bw3dl9kph6ucc?svg=true" style="max-width:100%;"></a>
<a href="http://codecov.io/github/checkstyle/checkstyle?branch=master"><img src="https://camo.githubusercontent.com/beebbd39c6fa8fa0b2f5f057062ec2b92dc89d7a/687474703a2f2f636f6465636f762e696f2f6769746875622f636865636b7374796c652f636865636b7374796c652f636f7665726167652e7376673f6272616e63683d6d6173746572" alt="" data-canonical-src="http://codecov.io/github/checkstyle/checkstyle/coverage.svg?branch=master" style="max-width:100%;"></a>
<a href="http://search.maven.org/#search%7Cgav%7C1%7Cg%3A%22com.puppycrawl.tools%22%20AND%20a%3A%22checkstyle%22"><img src="https://camo.githubusercontent.com/8e7229e5fee3561f051eb4e0bb59fce6e09e0129/68747470733a2f2f6d6176656e2d6261646765732e6865726f6b756170702e636f6d2f6d6176656e2d63656e7472616c2f636f6d2e7075707079637261776c2e746f6f6c732f636865636b7374796c652f62616467652e737667" alt="" data-canonical-src="https://maven-badges.herokuapp.com/maven-central/com.puppycrawl.tools/checkstyle/badge.svg" style="max-width:100%;"></a>
<a href="https://www.versioneye.com/user/projects/5504ca834a1064774400049a"><img src="https://camo.githubusercontent.com/40cf997f01d323fcac8b6f54087371cf69650e7e/68747470733a2f2f7777772e76657273696f6e6579652e636f6d2f757365722f70726f6a656374732f3535303463613833346131303634373734343030303439612f62616467652e737667" alt="" data-canonical-src="https://www.versioneye.com/user/projects/5504ca834a1064774400049a/badge.svg" style="max-width:100%;"></a>
<a href="http://nemo.sonarqube.org/dashboard/index/com.puppycrawl.tools:checkstyle"><img src="https://camo.githubusercontent.com/736022bf6c8a9e7902ccaf6d79e3353f4b1385a5/68747470733a2f2f6465762e65636c697073652e6f72672f736f6e61722f696d616765732f6c6f676f2e706e673f31343030373735353336" alt="" data-canonical-src="https://dev.eclipse.org/sonar/images/logo.png?1400775536" style="max-width:100%;"></a></p>

<p>Members chat: <a href="https://gitter.im/checkstyle"><img src="https://camo.githubusercontent.com/b1d949bfcc019b2c7a2fb434ff97744615f3cfe1/687474703a2f2f696d672e736869656c64732e696f2f62616467652f6769747465722d4a4f494e253230434841542d626c75652e737667" alt="" data-canonical-src="http://img.shields.io/badge/gitter-JOIN%20CHAT-blue.svg" style="max-width:100%;"></a>
Contributors chat: <a href="https://gitter.im/checkstyle/checkstyle?utm_source=badge&amp;utm_medium=badge&amp;utm_campaign=pr-badge&amp;utm_content=badge"><img src="https://camo.githubusercontent.com/da2edb525cde1455a622c58c0effc3a90b9a181c/68747470733a2f2f6261646765732e6769747465722e696d2f4a6f696e253230436861742e737667" alt="https://gitter.im/checkstyle/checkstyle" data-canonical-src="https://badges.gitter.im/Join%20Chat.svg" style="max-width:100%;"></a></p>

<p><a href="https://raw.githubusercontent.com/checkstyle/resources/master/img/checkstyle-logos/checkstyle-logo-260x99.png" target="_blank"><img src="https://raw.githubusercontent.com/checkstyle/resources/master/img/checkstyle-logos/checkstyle-logo-260x99.png" alt="" style="max-width:100%;"></a></p>

<p>Checkstyle is a tool for
checking Java source code for adherence to a Code Standard or set of validation rules (best practices).</p>

<p>The latest version can be found at <a href="http://sourceforge.net/projects/checkstyle/files/checkstyle/">SourceForge downloads</a> or at <a href="http://repo1.maven.org/maven2/com/puppycrawl/tools/checkstyle/">Maven repo</a>.</p>

<p>Documentation is available in HTML format, see <a href="http://checkstyle.sourceforge.net/checks.html">http://checkstyle.sourceforge.net/checks.html</a>.</p>

<h1><a id="user-content-continuous-integration-and-quality-reports" class="anchor" href="#continuous-integration-and-quality-reports" aria-hidden="true"><span class="octicon octicon-link"></span></a>Continuous integration and Quality reports</h1>

<p>Travis (Linux build): <a href="http://travis-ci.org/checkstyle/checkstyle"><img src="https://camo.githubusercontent.com/a2dd0c8c783478db0e6d87e58a2b6d0c050b738c/68747470733a2f2f7365637572652e7472617669732d63692e6f72672f636865636b7374796c652f636865636b7374796c652e706e67" alt="" data-canonical-src="https://secure.travis-ci.org/checkstyle/checkstyle.png" style="max-width:100%;"></a>
Appveyor (Windows build): <a href="https://ci.appveyor.com/project/checkstyle/checkstyle"><img src="https://camo.githubusercontent.com/05ff7560846e3a37cd8c779723e16af51de02dd2/68747470733a2f2f63692e6170707665796f722e636f6d2f6170692f70726f6a656374732f7374617475732f727736627733646c396b7068367563633f7376673d74727565" alt="" data-canonical-src="https://ci.appveyor.com/api/projects/status/rw6bw3dl9kph6ucc?svg=true" style="max-width:100%;"></a></p>

<p>Quality reports: <a href="http://checkstyle.sourceforge.net/project-reports.html">http://checkstyle.sourceforge.net/project-reports.html</a></p>

<h1><a id="user-content-feedbacksupport" class="anchor" href="#feedbacksupport" aria-hidden="true"><span class="octicon octicon-link"></span></a>Feedback/Support</h1>

<p>Please send any feedback to <a href="https://groups.google.com/forum/?hl=en#!forum/checkstyle">https://groups.google.com/forum/?hl=en#!forum/checkstyle</a></p>

<p>Questions and Answers from community: <a href="http://stackoverflow.com/questions/tagged/checkstyle"><img src="https://camo.githubusercontent.com/270d2bc60ae30bb90b74493311e2c158511793b2/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f737461636b6f766572666c6f772d434845434b5354594c452d626c75652e737667" alt="" data-canonical-src="https://img.shields.io/badge/stackoverflow-CHECKSTYLE-blue.svg" style="max-width:100%;"></a></p>

<p>Bugs and Feature requests: <a href="https://github.com/checkstyle/checkstyle/issues">https://github.com/checkstyle/checkstyle/issues</a></p>

<h1><a id="user-content-licensing" class="anchor" href="#licensing" aria-hidden="true"><span class="octicon octicon-link"></span></a>Licensing</h1>

<p><a href="/checkstyle/checkstyle/blob/master/LICENSE"><img src="https://camo.githubusercontent.com/f115facfd8258d6fad87c893f35b01a7b487adad/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f6c6963656e73652d474e552532304c47504c25323076322e312d626c75652e737667" alt="" data-canonical-src="https://img.shields.io/badge/license-GNU%20LGPL%20v2.1-blue.svg" style="max-width:100%;"></a></p>

<p>This software is licensed under the terms in the file named "LICENSE" in this
directory.</p>

<p>The software uses the ANTLR package (<a href="http://www.antlr.org">http://www.antlr.org</a>). Its license terms
are in the file named "RIGHTS.antlr" in this directory.</p>

<p>This product includes software developed by
The Apache Software Foundation (<a href="http://www.apache.org/">http://www.apache.org/</a>).</p>

<p>The software uses the Cli, Logging and Beanutils packages from the
Apache Commons project (<a href="http://commons.apache.org/">http://commons.apache.org/</a>). The license terms
of these packages are in the file named "LICENSE.apache20" in this
directory.</p>

<p>The software uses the Google Guava Libraries
(<a href="https://code.google.com/p/guava-libraries/">https://code.google.com/p/guava-libraries/</a>). The license terms of
these packages are in the file named "LICENSE.apache20" in this
directory.</p>
</article>
  </div>

</div>

<a href="#jump-to-line" rel="facebox[.linejump]" data-hotkey="l" style="display:none">Jump to Line</a>
<div id="jump-to-line" style="display:none">
  <!-- </textarea> --><!-- '"` --><form accept-charset="UTF-8" action="" class="js-jump-to-line-form" method="get"><div style="margin:0;padding:0;display:inline"><input name="utf8" type="hidden" value="&#x2713;" /></div>
    <input class="linejump-input js-jump-to-line-field" type="text" placeholder="Jump to line&hellip;" aria-label="Jump to line" autofocus>
    <button type="submit" class="btn">Go</button>
</form></div>

        </div>
      </div>
      <div class="modal-backdrop"></div>
    </div>
  </div>


    </div><!-- /.wrapper -->

      <div class="container">
  <div class="site-footer" role="contentinfo">
    <ul class="site-footer-links right">
        <li><a href="https://status.github.com/" data-ga-click="Footer, go to status, text:status">Status</a></li>
      <li><a href="https://developer.github.com" data-ga-click="Footer, go to api, text:api">API</a></li>
      <li><a href="https://training.github.com" data-ga-click="Footer, go to training, text:training">Training</a></li>
      <li><a href="https://shop.github.com" data-ga-click="Footer, go to shop, text:shop">Shop</a></li>
        <li><a href="https://github.com/blog" data-ga-click="Footer, go to blog, text:blog">Blog</a></li>
        <li><a href="https://github.com/about" data-ga-click="Footer, go to about, text:about">About</a></li>
        <li><a href="https://github.com/pricing" data-ga-click="Footer, go to pricing, text:pricing">Pricing</a></li>

    </ul>

    <a href="https://github.com" aria-label="Homepage">
      <span class="mega-octicon octicon-mark-github" title="GitHub"></span>
</a>
    <ul class="site-footer-links">
      <li>&copy; 2015 <span title="0.06792s from github-fe145-cp1-prd.iad.github.net">GitHub</span>, Inc.</li>
        <li><a href="https://github.com/site/terms" data-ga-click="Footer, go to terms, text:terms">Terms</a></li>
        <li><a href="https://github.com/site/privacy" data-ga-click="Footer, go to privacy, text:privacy">Privacy</a></li>
        <li><a href="https://github.com/security" data-ga-click="Footer, go to security, text:security">Security</a></li>
        <li><a href="https://github.com/contact" data-ga-click="Footer, go to contact, text:contact">Contact</a></li>
        <li><a href="https://help.github.com" data-ga-click="Footer, go to help, text:help">Help</a></li>
    </ul>
  </div>
</div>


    <div class="fullscreen-overlay js-fullscreen-overlay" id="fullscreen_overlay">
  <div class="fullscreen-container js-suggester-container">
    <div class="textarea-wrap">
      <textarea name="fullscreen-contents" id="fullscreen-contents" class="fullscreen-contents js-fullscreen-contents" placeholder="" aria-label=""></textarea>
      <div class="suggester-container">
        <div class="suggester fullscreen-suggester js-suggester js-navigation-container"></div>
      </div>
    </div>
  </div>
  <div class="fullscreen-sidebar">
    <a href="#" class="exit-fullscreen js-exit-fullscreen tooltipped tooltipped-w" aria-label="Exit Zen Mode">
      <span class="mega-octicon octicon-screen-normal"></span>
    </a>
    <a href="#" class="theme-switcher js-theme-switcher tooltipped tooltipped-w"
      aria-label="Switch themes">
      <span class="octicon octicon-color-mode"></span>
    </a>
  </div>
</div>



    
    

    <div id="ajax-error-message" class="flash flash-error">
      <span class="octicon octicon-alert"></span>
      <a href="#" class="octicon octicon-x flash-close js-ajax-error-dismiss" aria-label="Dismiss error"></a>
      Something went wrong with that request. Please try again.
    </div>


      <script crossorigin="anonymous" src="https://assets-cdn.github.com/assets/frameworks-df03cb524192fbb325ef264ca1bdee425481db0aadf702dd6d209d1fa0ccf572.js"></script>
      <script async="async" crossorigin="anonymous" src="https://assets-cdn.github.com/assets/github/index-e321a5911341a3613eea06a9cc3f6ea636b3cdc377824313d67ad604a5e8d9e1.js"></script>
      
      
    <div class="js-stale-session-flash stale-session-flash flash flash-warn flash-banner hidden">
      <span class="octicon octicon-alert"></span>
      <span class="signed-in-tab-flash">You signed in with another tab or window. <a href="">Reload</a> to refresh your session.</span>
      <span class="signed-out-tab-flash">You signed out in another tab or window. <a href="">Reload</a> to refresh your session.</span>
    </div>
  </body>
</html>

