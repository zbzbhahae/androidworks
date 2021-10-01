package com.zb.hiltapp;


/**
 * Hilt的一些默认组件
 *
 *
 * Component	                            Scope	                               Created at	                         Destroyed at
 * SingletonComponent	            @Singleton	                       Application#onCreate()	     Application#onDestroy()
 * ActivityRetainedComponent	    @ActivityRetainedScoped	   Activity#onCreate()1	         Activity#onDestroy()1
 * ViewModelComponent	            @ViewModelScoped	       ViewModel created	         ViewModel destroyed
 * ActivityComponent	                @ActivityScoped	               Activity#onCreate()	         Activity#onDestroy()
 * FragmentComponent	            @FragmentScoped	           Fragment#onAttach()	     Fragment#onDestroy()
 * ViewComponent	                    @ViewScoped	                   View#super()	                     View destroyed
 * ViewWithFragmentComponent	@ViewScoped	                   View#super()	                     View destroyed
 * ServiceComponent	                @ServiceScoped	               Service#onCreate()	         Service#onDestroy()
 * ActivityRetainedComponent lives across configuration changes, so it is created at the first onCreate and last onDestroy
 */



public class ReadMe {
}
