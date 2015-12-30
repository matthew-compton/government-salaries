//
//  ViewController.swift
//  government-salaries
//
//  Created by Matthew Compton on 12/29/15.
//  Copyright © 2015 Ambergleam. All rights reserved.
//

import UIKit
import Firebase

class ViewController: UIViewController {

    @IBOutlet var label: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        let ref = Firebase(url:"https://government-salaries.firebaseio.com/")
        ref.observeSingleEventOfType(.Value, withBlock: { snapshot in
            self.label.text = "There are \(snapshot.childrenCount) employees."
//            let enumerator = snapshot.children
//            while let rest = enumerator.nextObject() as? FDataSnapshot {
//                print(rest.value)
//            }
        })
        
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

